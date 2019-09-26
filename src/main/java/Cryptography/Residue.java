package Cryptography;

public class Residue implements Algorithm {
    protected int n, m;

    public Residue(){

    }

    public static Residue reduce(Residue a) {
        for (int i = a.n; i > 0; i--){
            if (gcd(i, a.m) == 1) {
                return new Residue(i, a.m);
            }
        }
        return a.copy();
    }


    public Residue(int n, int m) {
        this.n = n % m;
        this.m = m;
    }

    public static Residue sum(Residue a, Residue b) throws ResidueException {
        if (a.m != b.m)
            throw new ResidueException();
        return new Residue((a.n + b.n) % a.m, a.m);
    }

    public static Residue mul(Residue a, Residue b) throws ResidueException {
        if (a.m != b.m)
            throw new ResidueException();
        return new Residue((a.n * b.n) % a.m, a.m);
    }

    public Residue copy() {
        return new Residue(n, m);
    }

    public static Residue pow(Residue a, int n) {
        Residue number = a.copy();
        Residue result = new Residue(1, number.m);
        while (n > 0) {
            if ((n & 1) == 1) {
                result.n *= number.n;
                result.n %= number.m;
            }
            number.n *= number.n % number.m;
            n >>= 1;
        }
        return result;
    }

    private static int gcd(int a, int b) {
        return b > 0 ? gcd (b, a % b) : a;
    }


    @Override
    public String toString() {
        return String.format("{%d (%d)}", n, m);
    }

    @Override
    public void start(int n) {
        Residue a = new Residue(23, 25);
        Residue b = new Residue(4, 25);
        Residue pow = Residue.pow(b, 2);
        try {
            System.out.println(String.format("%s + %s = %s", a, b, Residue.sum(a, b)));
            System.out.println(String.format("%s * %s = %s", a, b, Residue.mul(a, b)));
            System.out.println(String.format("%s^%d = %s", b, 2, pow));
            System.out.println(String.format("Приведенная система вычетов для числа %d и модуля %d: %s", pow.n, pow.m, Residue.reduce(pow))); //*/
        } catch (ResidueException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printTask() {
        System.out.println("Напишите библиотеку для работы с полной системой вычетов по модулю n.  Реализуйте в вашей библиотеке арифметические операции, операцию возведения в целую степень.");
        System.out.println("Выведите на экран приведенную систему вычетов по модулю m");
    }
}