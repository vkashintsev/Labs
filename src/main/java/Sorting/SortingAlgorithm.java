package Sorting;

import java.util.Arrays;

public abstract class SortingAlgorithm {
    public abstract  <T extends Comparable> void sortAscending(T[] values); // 1 2 3
    public abstract  <T extends Comparable> void sortDescending(T[] values); // 3 2 1
    protected static <T> void swap(T[] values, int first, int second){
        T temp = values[first];
        values[first] = values[second];
        values[second] = temp;
    }
    public static <T> void printArray(T[] values) {
        System.out.println(Arrays.asList(values).toString());
    }
}
