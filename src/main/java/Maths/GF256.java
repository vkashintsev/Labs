package Maths;


public class GF256 {

    public static int nod(int a, int b){

        return 0;
    }

    public static long getGF(String polynom) {
        polynom = polynom.replace('-', '+');
        long result = 0;
        String[] vars;
        vars = polynom.split("\\+");
        long degr = 0;

        for (String varStr : vars) {
            if (varStr.length() == 1 && Character.isDigit(varStr.charAt(0))) {
                result ^= Integer.parseInt(varStr);
            } else {
                degr = Integer.parseInt(varStr.substring(2));
                result ^= (long) Math.pow(2, degr);
            }
        }
        return result;
    }

    public static long sum(long a, long b) {
        return a ^ b;
    }

    public static String toString(long gf) {
        StringBuffer result = new StringBuffer();
        if (gf == 0)
            return "0";
        if ((gf & 1) != 0)
            result.append("1");
        if ((gf & 1 << 1) != 0){
            result.append(result.length() == 0 ? "x + " : " + x +");
        }
        for (long i = 2; i < 32; i++) {
            if ((gf & 1 << i) != 0) {
                result.append(" x^" + i + " + ");
            }
        }
        result.deleteCharAt(result.length() - 2);
        result.append(String.format("  (0b%sL)", Long.toBinaryString(gf)));
        return result.toString();
    }

    public static long polynomeMul(long a, long b) {
        long res = 0;
        for (long i = 0; i < 8; i++) {
            res ^= a * b & (1 << i);
        }
        return res;
    }
    public static long mul(long a, long b) {
        long mul = polynomeMul(a, b);
        long basePolynome = 0x111011011L;
        return mul ^ polynomeMul(basePolynome, mul >> 32);
    }
    public static long gPow(long a, long n) {
        if (n == 0) {
            return 1;
        } else if (n % 2 == 0) {
            return gPow(mul(a, a), n / 2);
        } else {
            long square = mul(a, a);
            return mul(gPow(square, n / 2), a);
        }
    }
    public static long inv(long firstOperand){
        return gPow(firstOperand, 254);
    }

    public static long mulByMod(long firstOperand, long secondOperand, long modulo) {
        long result = 0;
        for (long i = 0; i < 8; i++) {
            for (long j = 0; j < 8; j++) {
                result ^=  ((((firstOperand >> i) & 1) & ((secondOperand >> j) & 1)) << (i + j));
            }
        }
        return mod(result, modulo);
    }

    private static long mod(long firstOperand, long secondOperand) {
        if (firstOperand == 0) {
            return 0;
        }
        if (firstOperand == 1) {
            if (secondOperand == 1) {
                return 0;
            }
            return 1;
        }

        long firstOperandMaxPower = 0;
        long secondOperandMaxPower = 0;
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