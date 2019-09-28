package Cipher;

import Cipher.Key.Key;
import Cipher.Key.PaillierKey;
import Maths.BigIntegerGenerator;
import javafx.util.Pair;

import java.math.BigInteger;
import java.util.Random;

public class PaillierCipher extends AsymmetricCipher {

    public PaillierCipher(PaillierKey key) {
        super(key);
    }


    @Override
    public BigInteger[] encrypt(BigInteger[] message) {
        PaillierKey key = (PaillierKey) getKey();
        BigInteger g = key.getPublicKey().getValue(), n = key.getPublicKey().getKey();
        BigInteger nsquare = n.multiply(n);
        BigInteger[] encrypted = new BigInteger[message.length];
        BigInteger r = BigIntegerGenerator.nextInt("2", n.toString());
        for(int i = 0; i < message.length; i++)
            encrypted[i] = g.modPow(message[i], nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);
        return encrypted;
    }

    @Override
    public BigInteger[] decrypt(BigInteger[] message) {
        PaillierKey key = (PaillierKey) getKey();
        BigInteger g = key.getPublicKey().getValue(), n = key.getPublicKey().getKey();
        BigInteger nsquare = n.multiply(n), lambda = key.getPrivateKey().getKey();
        //privateKey = new Pair<>(lambda, mu);
        BigInteger[] decrypted = new BigInteger[message.length];
        BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
        for(int i = 0; i < message.length; i++)
            decrypted[i] = message[i].modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
        return decrypted;
    }
}
