package Maths;

import java.math.BigInteger;

public class Maths {
    public static boolean isTheNumberSimple(BigInteger n) {
        if (n.compareTo(BigInteger.TWO) < 0)
            return false;

        if (n.compareTo(BigInteger.TWO) == 0)
            return true;

        for (BigInteger i = BigInteger.TWO; i.compareTo(n) < 0; i = i.add(BigInteger.ONE))
            if (n.mod(i).compareTo(BigInteger.ZERO) == 0)
                return false;

        return true;
    }
    public static BigInteger powMod(BigInteger num, BigInteger pow, BigInteger mod) {
        BigInteger result = BigInteger.ONE;
        while (pow.compareTo(BigInteger.ZERO) > 0) {
            if (pow.and(BigInteger.ONE).compareTo(BigInteger.ONE) == 0) {
                result = result.multiply(num);
                result = result.mod(mod);
            }
            num = num.multiply(num.mod(mod));
            pow = pow.shiftRight(1);
        }
        return result;
    }
}
