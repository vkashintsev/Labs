package Sorting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable> void sortAscending(List<T> values) {
        mergeSort(values, values.size(), true);
    }

    @Override
    public <T extends Comparable> void sortDescending(List<T> values) {
        mergeSort(values, values.size(), false);
    }

    private <T extends Comparable> void mergeSort(List<T> values, int n, boolean ascending) {
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        List<T> left = new ArrayList<>(values.subList(0, middle));
        List<T> right = new ArrayList<>(values.subList(middle, values.size()));
        mergeSort(left, middle, ascending);
        mergeSort(right, n - middle, ascending);
        merge(values, left, right, middle, n - middle, ascending);
    }

    private <T extends Comparable>  void merge(List<T> values, List<T> left, List<T> right, int leftIndex, int rightIndex, boolean ascending) {
        int i = 0, j = 0, k = 0;
        while (i < leftIndex && j < rightIndex) {
            if ((!ascending ? -1 : 1) * left.get(i).compareTo(right.get(j)) < 0) {
                values.set(k++, left.get(i++));
            }
            else {
                values.set(k++, right.get(j++));
            }
        }
        while (i < leftIndex) {
            values.set(k++, left.get(i++));
        }
        while (j < rightIndex) {
            values.set(k++, right.get(j++));
        }
    }
}
