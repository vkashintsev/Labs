package Cipher;

import Cipher.Key.SymmetricKey;
import Maths.GF256;

public class RijndaelCipher extends SymmetricCipher {
    private static char[] sBox;
    private static char[] inversedSBox;
    private static char[][] multiplyByModulo283Remainders;
    private static char[] cPolynomial;
    private static char[] inversedCPolynomial;
    private static char[] rCon;

    private char[] key;
    private char[][] roundKeys;
    private int roundsCount;


    {
        cPolynomial = new char[] {
                2, 3, 1, 1,
                1, 2, 3, 1,
                1, 1, 2, 3,
                3, 1, 1, 2
        };
        inversedCPolynomial = new char[] {
                14, 11, 13, 9,
                9, 14, 11, 13,
                13, 9, 14, 11,
                11, 13, 9, 14
        };
        rCon = new char[] { 1, 2, 4, 8, 16, 32, 64, 127, 27, 54 };
        sBox = new char[256];
        inversedSBox = new char[256];
    }
    public RijndaelCipher(SymmetricKey key) {
        super(key);
        byte[] md5Key = key.getMd5Key();
        this.key = new char[md5Key.length];
        for (int i = 0; i < md5Key.length; i++)
            this.key[i] = (char)  Byte.toUnsignedInt(md5Key[i]);

        createMultiplyByModulo283Remainders();
        createSBox();
        createInversedSBox(sBox);
        createRoundKeys();
    }
    @Override
    public byte[] encrypt(byte[] message) {
        char[] state = new char[message.length];
        for (int i = 0; i < message.length; i++)
            state[i] = (char) Byte.toUnsignedInt(message[i]);
        addRoundKey(state, key);
        for (int round = 0; round < roundsCount - 1; round++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, roundKeys[round]);
        }
        subBytes(state);
        shiftRows(state);
        addRoundKey(state, roundKeys[roundsCount - 1]);
        byte[] result = new byte[state.length];
        for (int i = 0; i < message.length; i++)
            result[i] = (byte) state[i];
        return result;
    }
    @Override
    public byte[] decrypt(byte[] message) {
        char[] state = new char[message.length];
        for (int i = 0; i < message.length; i++)
            state[i] = (char) Byte.toUnsignedInt(message[i]);
        addRoundKey(state, roundKeys[roundKeys.length-1]);
        for (int round = 0; round < roundsCount - 1; round++) {
            inversedShiftRows(state);
            inversedSubBytes(state);
            addRoundKey(state, roundKeys[roundsCount - round-2]);
            inversedMixColumns(state);
        }
        inversedShiftRows(state);
        inversedSubBytes(state);
        addRoundKey(state, key);
        byte[] result = new byte[state.length];
        for (int i = 0; i < message.length; i++)
            result[i] = (byte) state[i];
        return result;
    }

    public void setKey(char[] key) {
        this.key = key.clone();
        createRoundKeys();
    }

    private static void createMultiplyByModulo283Remainders() {
        char[][] result = new char[256][256];
        GF256 GF256 = new GF256();
        for (int i = 0; i < 256; i++) {
            for(int j = i; j < 256; j++){
                result[i][j] = (char) GF256.mulByMod(i, j, 283);
                if (i != j) {
                    result[j][i] = result[i][j];
                }
            }
        }
        multiplyByModulo283Remainders = result;
    }

    private static int ROTL8(int x, int shift) {
        return ( ((x) << (shift)) | ((x) >> (8 - (shift)))) % 256 ;
    }

    private static void createSBox() {
        int p = 1, q = 1;
        do {
            p = (p ^ (p << 1) ^ ((p & 0x80) != 0 ? 0x1B : 0)) % 256;
            q = (q ^ q << 1) % 256;
            q = (q ^ q << 2) % 256;
            q = (q ^ q << 4) % 256;
            q = (q ^ ((q & 0x80) != 0 ? 0x09 : 0)) % 256;
            int xformed = q ^ ROTL8(q, 1);
            xformed ^= ROTL8(q, 2);
            xformed ^= ROTL8(q, 3);
            xformed ^= ROTL8(q, 4);
            sBox[p] = (char) (xformed ^ 0x63);
        } while (p != 1);
        sBox[0] = 0x63;
    }

    private static void createInversedSBox(char[] sBox) {
        for (char i = 0; i < 256; i++)
            for (char j = 0; j < 256; j++)
                if (sBox[j] == i) {
                    inversedSBox[i] = j;
                    break;
                }
    }

    private void createRoundKeys() {
        roundsCount =  (key.length / 4 + 6);
        roundKeys = new char[roundsCount][];
        for (int i = 0; i < roundsCount; i++) {
            roundKeys[i] = new char[key.length];
            if (i == 0) {
                int[] W = new int[]{
                        Byte.toUnsignedInt((byte)key[key.length - 3]),
                        Byte.toUnsignedInt((byte) key[key.length - 2]),
                        Byte.toUnsignedInt((byte) key[key.length - 1]),
                        Byte.toUnsignedInt((byte) key[key.length - 4])
                };
                for (int j = 0; j < 4; j++) {
                    roundKeys[i][j] = (char) Byte.toUnsignedInt((byte) (key[j] ^ sBox[W[j]] ^ (j == 0 ? rCon[i % rCon.length] : 0)));
                }
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 4; k++) {
                        roundKeys[i][(j + 1) * 4 + k] =  (char) Byte.toUnsignedInt((byte) (key[(j + 1) * 4 + k] ^ roundKeys[i][j * 4 + k]));
                    }
                }
            } else {
                int[] W = new int[]{
                        roundKeys[i - 1][key.length - 3],
                        roundKeys[i - 1][key.length - 2],
                        roundKeys[i - 1][key.length - 1],
                        roundKeys[i - 1][key.length - 4]
                };
                for (int j = 0; j < 4; j++) {
                    roundKeys[i][j] = (char)  Byte.toUnsignedInt((byte)(roundKeys[i - 1][j] ^ sBox[W[j]] ^ (j == 0 ? rCon[i % rCon.length] : 0)));
                }
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 4; k++) {
                        roundKeys[i][(j + 1) * 4 + k] = (char) Byte.toUnsignedInt((byte) (roundKeys[i - 1][(j + 1) * 4 + k] ^ roundKeys[i][j * 4 + k]));
                    }
                }
            }
        }
    }

    private void addRoundKey(char[] state, char[] roundkey) {
        for (int i = 0; i < state.length; i++) {
            state[i] ^= Byte.toUnsignedInt((byte) roundkey[i]);
        }
    }

    private void subBytes(char[] state) {
        for (int i = 0; i < state.length; i++) {
            state[i] = sBox[state[i]];
        }
    }

    private void inversedSubBytes(char[] state) {
        for (int i = 0; i < state.length; i++) {
            state[i] = inversedSBox[state[i]];
        }
    }

    private void shiftRows(char[] state) {
        char temporary = state[1];
        state[1] = state[5];
        state[5] = state[9];
        state[9] = state[13];
        state[13] = temporary;
        temporary = state[2];
        state[2] = state[10];
        state[10] = temporary;
        temporary = state[6];
        state[6] = state[14];
        state[14] = temporary;
        temporary = state[3];
        state[3] = state[15];
        state[15] = state[11];
        state[11] = state[7];
        state[7] = temporary;
    }

    private void inversedShiftRows(char[] state) {
        char temporary = state[1];
        state[1] = state[13];
        state[13] = state[9];
        state[9] = state[5];
        state[5] = temporary;
        temporary = state[2];
        state[2] = state[10];
        state[10] = temporary;
        temporary = state[6];
        state[6] = state[14];
        state[14] = temporary;
        temporary = state[3];
        state[3] = state[7];
        state[7] = state[11];
        state[11] = state[15];
        state[15] = temporary;
    }

    private void mixColumns(char[] state) {
        char[] temporaryState = new char[state.length];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temporaryState[i * 4 + j]  = multiplyByModulo283Remainders[cPolynomial[j * 4 + 0]][state[i * 4 + 0]];
                temporaryState[i * 4 + j] ^= multiplyByModulo283Remainders[cPolynomial[j * 4 + 1]][state[i * 4 + 1]];
                temporaryState[i * 4 + j] ^= multiplyByModulo283Remainders[cPolynomial[j * 4 + 2]][state[i * 4 + 2]];
                temporaryState[i * 4 + j] ^= multiplyByModulo283Remainders[cPolynomial[j * 4 + 3]][state[i * 4 + 3]];
            }
        }
        for (int i = 0; i < state.length; i++) {
            state[i] = temporaryState[i];
        }
    }

    private void inversedMixColumns(char[] state) {
        char[] temporaryState = new char[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temporaryState[i * 4 + j]  = multiplyByModulo283Remainders[inversedCPolynomial[j * 4 + 0]][state[i * 4 + 0]];
                temporaryState[i * 4 + j] ^= multiplyByModulo283Remainders[inversedCPolynomial[j * 4 + 1]][state[i * 4 + 1]];
                temporaryState[i * 4 + j] ^= multiplyByModulo283Remainders[inversedCPolynomial[j * 4 + 2]][state[i * 4 + 2]];
                temporaryState[i * 4 + j] ^= multiplyByModulo283Remainders[inversedCPolynomial[j * 4 + 3]][state[i * 4 + 3]];
            }
        }
        for (int i = 0; i < state.length; i++) {
            state[i] = temporaryState[i];
        }
    }


}
