package Sorting;

import java.util.List;

public class QuickSort extends SortingAlgorithm{

    @Override
    public <T extends Comparable> void sortAscending(List<T> values) {
        quickSort(values, 0, values.size()-1, true);
    }

    @Override
    public <T extends Comparable> void sortDescending(List<T> values) {
        quickSort(values, 0, values.size()-1, false);
    }

    private static <T extends Comparable> void quickSort(List<T> values, int low, int high, boolean ascending) {
        if (values.size() == 0 || low >= high)
            return;
        int i = low, j = high;
        T middle = values.get(low + (high - low) / 2);
        while (i <= j) {
            while ((!ascending ? -1 : 1) * values.get(i).compareTo(middle) < 0) {
                i++;
            }
            while ((!ascending ? -1 : 1) * values.get(j).compareTo(middle) > 0) {
                j--;
            }
            if (i <= j) {
                swap(values, i++, j--);
            }
        }

        if (low < j)
            quickSort(values, low, j, ascending);
        if (high > i)
            quickSort(values, i, high, ascending);
    }



 }
