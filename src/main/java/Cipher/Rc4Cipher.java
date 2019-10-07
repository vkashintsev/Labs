package Cipher;
import Cipher.Key.SymmetricKeys.Rc4Key;
import Cipher.Key.SymmetricKeys.SymmetricKey;


public class Rc4Cipher extends SymmetricCipher {
    public Rc4Cipher(Rc4Key key) {
        super(key);
    }


    private byte[] compute(byte[] message) {
        char[] key =  ((SymmetricKey)this.key).getKey();
        byte[] result = new byte[message.length];
        int x, y, j = 0;
        int[] perm = new int[256];

        for (int i = 0; i < 256; i++) {
            perm[i] = i;
        }

        for (int i = 0; i < 256; i++) {
            j = (key[i % key.length] + perm[i] + j) % 256;
            x = perm[i];
            perm[i] = perm[j];
            perm[j] = x;
        }

        for (int i = 0; i < message.length; i++) {
            y = i % 256;
            j = (perm[y] + j) % 256;
            x = perm[y];
            perm[y] = perm[j];
            perm[j] = x;
            result[i] = ((byte)( ((int) message[i]) ^ perm[(perm[y] + perm[j]) % 256]));
        }
        return result;
    }



    @Override
    public byte[] encrypt(byte[] message) {
        byte[] bytes = compute(message);
        return bytes;
    }

    @Override
    public byte[] decrypt(byte[] message) {
        byte[] decrypted = compute( message);
        return decrypted;
    }

}

