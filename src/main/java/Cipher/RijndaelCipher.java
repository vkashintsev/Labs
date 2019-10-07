package Cipher;

import Cipher.Key.RijndaelKey;

import static Cipher.Constants.RijndaelConstants.*;

public class RijndaelCipher extends SymmetricCipher {


    public RijndaelCipher(RijndaelKey key) {
        super(key);
    }

    @Override
    public byte[] encrypt(byte[] message) {
        RijndaelKey key = (RijndaelKey) getKey();
        char[] state = new char[message.length];
        int roundsCount = key.getRoundsCount();
        for (int i = 0; i < message.length; i++)
            state[i] = (char) Byte.toUnsignedInt(message[i]);
        addRoundKey(state, key.getKey());
        for (int round = 0; round < roundsCount - 1; round++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, key.getRoundKeys()[round]);
        }
        subBytes(state);
        shiftRows(state);
        addRoundKey(state, key.getRoundKeys()[roundsCount - 1]);
        byte[] result = new byte[state.length];
        for (int i = 0; i < message.length; i++)
            result[i] = (byte) state[i];
        return result;
    }
    @Override
    public byte[] decrypt(byte[] message) {
        RijndaelKey key = (RijndaelKey) getKey();
        char[] state = new char[message.length];
        int roundsCount = key.getRoundsCount();
        for (int i = 0; i < message.length; i++)
            state[i] = (char) Byte.toUnsignedInt(message[i]);
        addRoundKey(state, key.getRoundKeys()[key.getRoundKeys().length-1]);
        for (int round = 0; round < roundsCount - 1; round++) {
            inversedShiftRows(state);
            inversedSubBytes(state);
            addRoundKey(state, key.getRoundKeys()[roundsCount - round-2]);
            inversedMixColumns(state);
        }
        inversedShiftRows(state);
        inversedSubBytes(state);
        addRoundKey(state, key.getKey());
        byte[] result = new byte[state.length];
        for (int i = 0; i < message.length; i++)
            result[i] = (byte) state[i];
        return result;
    }

    public void setKey(RijndaelKey key) {
        this.key = key;
    }

    public void addRoundKey(char[] state, char[] roundkey) {
        for (int i = 0; i < state.length; i++) {
            state[i] ^= Byte.toUnsignedInt((byte) roundkey[i]);
        }
    }
   //#region SubBytes
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
    //#endregion
    //region Shift Rows
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
   //#endregion
    //#region Mix Columns
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
    //endregion

}
