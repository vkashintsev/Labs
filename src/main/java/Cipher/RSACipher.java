package Cipher;
import Cipher.Key.PublicKeys.RSAKey;

import java.math.BigInteger;

public class RSACipher extends AsymmetricCipher {
    BigInteger N;
    BigInteger E, D;

    public RSACipher(RSAKey key) {
        super(key);
        E = key.getPrivateKey().getKey();
        D = key.getPublicKey().getKey();
        N = key.getPrivateKey().getValue();
    }

    @Override
    public BigInteger[] encrypt(BigInteger[] message) {
        BigInteger[] encrypted = new BigInteger[message.length];
        for(int i = 0; i < message.length; i++)
            encrypted[i] = message[i].modPow(E, N);
        return encrypted;
    }


    @Override
    public BigInteger[] decrypt(BigInteger[] message) {
        BigInteger[] decrypted = new BigInteger[message.length];
        for(int i = 0; i < decrypted.length; i++)
            decrypted[i] = message[i].modPow(D, N);
        return decrypted;
    }
}
