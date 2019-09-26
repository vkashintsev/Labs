package Cryptography;

public class Euler implements Algorithm {
    @Override
    public void start(int n) {
        int result = n;
        for (int i=2; i*i<=n; ++i)
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        if (n > 1)
            result -= result / n;
        System.out.println(result);
    }

    @Override
    public void printTask() {
        System.out.println("Напишите функцию, вычисляющую значение φ(m), где φ(m)-функция Эйлера.");
    }
}
