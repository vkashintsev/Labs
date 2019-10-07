package Cipher.Constants;

import Maths.GF256;

public class RijndaelConstants {
    public static final char[] sBox;
    public static final char[] inversedSBox;
    public static final char[] cPolynomial;
    public static final char[] inversedCPolynomial;
    public static final char[] rCon;
    public static final char[][] multiplyByModulo283Remainders;

    static {
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
        multiplyByModulo283Remainders = new char[256][256];

        for (int i = 0; i < 256; i++) {
            for(int j = i; j < 256; j++){
                multiplyByModulo283Remainders[i][j] = (char) GF256.mulByMod(i, j, 283);
                if (i != j) {
                    multiplyByModulo283Remainders[j][i] = multiplyByModulo283Remainders[i][j];
                }
            }
        }
        createSBox();
        createInversedSBox(sBox);
    }

    //#region sBox
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
    //#endregion

}
