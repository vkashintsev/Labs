package Sorting;

import java.util.List;

public class InsertionSort extends SortingAlgorithm {
    @Override
    public void sortAscending(List<Comparable> values) {
        for (int i = 1; i < values.size(); i++) {
            for (int j = i; j > 0 &&  values.get(j-1).compareTo(values.get(j)) > 0; j--) {
                swap(values, j - 1, j);
            }
        }
    }

    @Override
    public void sortDescending(List<Comparable> values) {
        for (int i = 1; i < values.size(); i++) {
            for (int j = i; j > 0 &&  values.get(j-1).compareTo(values.get(j)) < 0; j--) {
                swap(values, j - 1, j);
            }
        }
    }
}
