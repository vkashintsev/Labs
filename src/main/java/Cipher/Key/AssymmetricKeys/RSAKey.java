package Cipher.Key.AssymmetricKeys;

import javafx.util.Pair;

import java.math.BigInteger;
import java.util.Random;

public class RSAKey extends AssymmetricKey {
    private Pair<BigInteger, BigInteger> publicKey;
    private Pair<BigInteger, BigInteger> privateKey;
    int primeSize;
    BigInteger p, q;
    BigInteger N;
    BigInteger r; // r = (p - 1) * (q - 1) эйлер
    BigInteger E, D;

    public RSAKey(int primeSize) {
        this.primeSize = primeSize;
        generatePrimeNumbers();
        generatePublicPrivateKeys();
        privateKey = new Pair<>(E, N);
        publicKey = new Pair<>(D, N);
    }

    // p, q
    private void generatePrimeNumbers() {
        p = new BigInteger(primeSize, 10, new Random());
        do {
            q = new BigInteger(primeSize, 10, new Random());
        }
        while(q.compareTo(p) == 0);
    }

    private void generatePublicPrivateKeys() {
        N = p.multiply(q);
        r = p.subtract(BigInteger.valueOf(1));
        r = r.multiply(q.subtract(BigInteger.valueOf(1)));
        do {
            E = new BigInteger(2 * primeSize, new Random());
        }
        while((E.compareTo(r) != -1) || (E.gcd(r).compareTo(BigInteger.valueOf(1)) != 0));
        D = E.modInverse(r);
    }

    public Pair<BigInteger, BigInteger> getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(Pair<BigInteger, BigInteger> publicKey) {
        this.publicKey = publicKey;
    }

    public Pair<BigInteger, BigInteger> getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(Pair<BigInteger, BigInteger> privateKey) {
        this.privateKey = privateKey;
    }
}
