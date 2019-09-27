package Sorting;

import java.util.Arrays;
import java.util.List;

public abstract class SortingAlgorithm {
    public abstract  void sortAscending(List<Comparable> values); // 1 2 3
    public abstract  void sortDescending(List<Comparable> values); // 3 2 1
    protected static void swap(List<Comparable> values, int first, int second){
        Comparable temp = values.get(first);
        values.set(first, values.get(second));
        values.set(second, temp);
    }
    public static void printArray(List<Comparable> values) {
        System.out.println(values.toString());
    }
}
