package Cryptography;

public class Example {
    Algorithm algorithm;
    public Example(Algorithm algorithm){
        this.algorithm = algorithm;
    }
    public void start(String caption, int n){
        System.out.println(caption);
        algorithm.printTask();
        algorithm.start(n);
    }
}
