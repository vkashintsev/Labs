package Maths;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerGenerator {
    public static BigInteger nextInt(String minValue, String maxValue){
        BigInteger maxLimit = new BigInteger(maxValue);
        BigInteger minLimit = new BigInteger(minValue);
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);
       return res;
    }
    public static BigInteger nextPrime(int length){
        return BigInteger.probablePrime(length, new Random());
    }
}
