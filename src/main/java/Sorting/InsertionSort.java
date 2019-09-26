package Sorting;

public class InsertionSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable> void sortAscending(T[] values) {
        for (int i = 1; i < values.length; i++) {
            for (int j = i; j > 0 &&  values[j - 1].compareTo(values[j]) > 0; j--) {
                swap(values, j - 1, j);
            }
        }
    }

    @Override
    public <T extends Comparable> void sortDescending(T[] values) {
        for (int i = 1; i < values.length; i++) {
            for (int j = i; j > 0 &&  values[j - 1].compareTo(values[j]) < 0; j--) {
                swap(values, j - 1, j);
            }
        }
    }
}
