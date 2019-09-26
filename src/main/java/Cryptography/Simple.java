package Cryptography;

public class Simple implements Algorithm {
    public Simple(){

    }

    @Override
    public void start(int n) {
        boolean prime;
        for(int i = 2; i < n; i++){
            prime = true;
            double sqrt = Math.sqrt(i);
            for(int j = 2; j < sqrt+1; j++)
                if(i % j==0) {
                    prime=false;
                    break;
                }
            if(prime)
                System.out.println(i);
        }
    }

    @Override
    public void printTask() {
        System.out.println("Напишите программу, выводящую все простые числа, которые меньше  m.");
    }
}
