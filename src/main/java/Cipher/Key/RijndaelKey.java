package Cipher.Key;

import static Cipher.Constants.RijndaelConstants.rCon;
import static Cipher.Constants.RijndaelConstants.sBox;

public class RijndaelKey extends SymmetricKey {
    private char[] key;
    private char[][] roundKeys;
    private int roundsCount;

    public RijndaelKey(char[] key) {
        super(key);
        byte[] md5Key = getMd5Key();
        this.key = new char[md5Key.length];
        for (int i = 0; i < md5Key.length; i++)
            this.key[i] = (char) Byte.toUnsignedInt(md5Key[i]);
        createRoundKeys();
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

    public char[][] getRoundKeys() {
        return roundKeys;
    }

    public int getRoundsCount(){
        return roundsCount;
    }


}
