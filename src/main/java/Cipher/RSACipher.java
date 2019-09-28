package Cipher;
import Cipher.Key.RSAKey;
import Maths.Converter;

import java.math.BigInteger;

public class RSACipher extends Cipher {
    BigInteger N;
    BigInteger E, D;

    public RSACipher(RSAKey key) {
        super(key);
        E = key.getPrivateKey().getKey();
        D = key.getPublicKey().getKey();
        N = key.getPrivateKey().getValue();
    }



    @Override
    public String encrypt(String message) {
        BigInteger[] bigDigits = Converter.textToBigArray(message);
        BigInteger[] encrypted = new BigInteger[bigDigits.length];
        for(int i = 0; i < bigDigits.length; i++)
            encrypted[i] = bigDigits[i].modPow(E, N);
        return Converter.bigArrayToHex(encrypted);
    }


    @Override
    public String decrypt(String message) {
        BigInteger[] encrypted = Converter.hexToBigArray(message);
        BigInteger[] decrypted = new BigInteger[encrypted.length];

        for(int i = 0; i < decrypted.length; i++)
            decrypted[i] = encrypted[i].modPow(D, N);
        return Converter.bigArrayToText(decrypted);
    }
}
