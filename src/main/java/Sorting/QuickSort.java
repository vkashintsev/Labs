package Sorting;

import java.util.List;

public class QuickSort extends SortingAlgorithm{

    @Override
    public void sortAscending(List<Comparable> values) {
        quickSort(values, 0, values.size()-1, true);
    }

    @Override
    public void sortDescending(List<Comparable> values) {
        quickSort(values, 0, values.size()-1, false);
    }

    private static void quickSort(List<Comparable> values, int low, int high, boolean ascending) {
        if (values.size() == 0 || low >= high)
            return;
        int i = low, j = high;
        Comparable middle = values.get(low + (high - low) / 2);
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
