package Maths;


public class GF256 {

    public static long sum(long a, long b) {
        return a ^ b;
    }

    public static long mul(long a, long b) {
        long p = 0;
        while (b != 0) {
            if ((b & 1) != 0)
                p ^= a;
            a <<= 1;
            b >>= 1;
        }
        return p;
    }

    public static long pow(long a, long n) {
        if (n == 0) {
            return 1;
        } else if (n % 2 == 0) {
            return pow(mul(a, a), n / 2);
        } else {
            long square = mul(a, a);
            return mul(pow(square, n / 2), a); // a * (a*a)^[n/2]
        }
    }

    public static long inv(long a) {
        return pow(a, 255);
    }

    public static String toString(long gf) {
        StringBuffer result = new StringBuffer();
        if (gf == 0)
            return "0";
        for (long i = 0; i < 32; i++) {
            if ((gf & 1 << i) != 0) {
                result.append(i == 0 ? i + 1 + "+" : "x^" + i + "+");
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append(String.format("  (0b%sL)", Long.toBinaryString(gf)));
        return result.toString();
    }


    public long mulByMod(int firstOperand, int secondOperand, int modulo) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result ^=  ((((firstOperand >> i) & 1) & ((secondOperand >> j) & 1)) << (i + j));
            }
        }
        return divisionRemainder(result, modulo);
    }

    private long divisionRemainder(long firstOperand, int secondOperand) {
        if (firstOperand == 0) {
            return 0;
        }
        if (firstOperand == 1) {
            if (secondOperand == 1) {
                return 0;
            }
            return 1;
        }
        int firstOperandMaxPower = 0;
        int secondOperandMaxPower = 0;
        long firstOperandCopy = firstOperand, secondOperandCopy = secondOperand;
        while (firstOperandCopy != 1) {
            firstOperandCopy >>= 1;
            firstOperandMaxPower++;
        }
        while (secondOperandCopy != 1) {
            secondOperandCopy >>= 1;
            secondOperandMaxPower++;
        }
        while (firstOperandMaxPower >= secondOperandMaxPower) {
            firstOperand ^= (secondOperand << (firstOperandMaxPower - secondOperandMaxPower));
            firstOperandMaxPower = 0;
            firstOperandCopy = firstOperand;
            while (firstOperandCopy != 1) {
                firstOperandCopy >>= 1;
                firstOperandMaxPower++;
            }
        }
        return firstOperand;
    }
}