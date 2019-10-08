package Cipher.Key.AssymmetricKeys;

import Maths.BigIntegerGenerator;
import javafx.util.Pair;

import java.math.BigInteger;

public class PaillierKey extends AssymmetricKey {
    private Pair<BigInteger, BigInteger> publicKey;
    private Pair<BigInteger, BigInteger> privateKey;

    private BigInteger L (BigInteger u, BigInteger n) {
        return u.subtract(BigInteger.ONE).divide(n);
    }

    public PaillierKey(int bitLength) {
        BigInteger p, q, n, g, lambda, mu, nsquare;
        do {
            p = BigIntegerGenerator.nextPrime(bitLength / 2);
            q = BigIntegerGenerator.nextPrime(bitLength / 2);
        } while (p.multiply(q).gcd(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))) == BigInteger.ONE);
        n = p.multiply(q);
        nsquare = n.multiply(n);
        do {
            g = BigIntegerGenerator.nextInt("2", n.toString());
            lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(
                    p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE))); // lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1)

        } while (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1);
        mu = L(g.modPow(lambda, nsquare), n).modInverse(n);
        publicKey = new Pair<>(n, g);
        privateKey = new Pair<>(lambda, mu);
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
