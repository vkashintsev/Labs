package Sorting;

import java.util.Arrays;
import java.util.List;

public abstract class SortingAlgorithm {
    public abstract  <T extends Comparable> void sortAscending(List<T> values); // 1 2 3
    public abstract  <T extends Comparable> void sortDescending(List<T> values); // 3 2 1
    protected static <T> void swap(List<T> values, int first, int second){
        T temp = values.get(first);
        values.set(first, values.get(second));
        values.set(second, temp);
    }
    public static <T> void printArray(List<T> values) {
        System.out.println(values.toString());
    }
}
