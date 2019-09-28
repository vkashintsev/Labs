package Cipher;
import Cipher.Key.SymmetricKey;
import Maths.Converter;


public class Rc4Cipher extends Cipher {
    public Rc4Cipher(SymmetricKey key) {
        super(key);
    }


    private byte[] compute(byte[] message) {
        byte[] key =  ((SymmetricKey)this.key).getKey().getBytes();
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
    public String encrypt(String message) {
        byte[] bytes = compute(message.getBytes());
        return Converter.byteToHex(bytes);
    }

    @Override
    public String decrypt(String message) {
        byte[] decrypted = compute( Converter.hexToByte(message));
        return Converter.byteToText(decrypted);
    }

}
