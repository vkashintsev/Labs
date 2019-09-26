package Sorting;

public class QuickSort extends SortingAlgorithm{

    @Override
    public <T extends Comparable> void sortAscending(T[] values) {
        ascending(values, 0, values.length-1);
    }

    @Override
    public <T extends Comparable> void sortDescending(T[] values) {
        descending(values, 0, values.length-1);
    }

    private static <T extends Comparable> void ascending(T[] values, int low, int high) {
        if (values.length == 0 || low >= high)
            return;
        int i = low, j = high;
        T middle = values[low + (high - low) / 2];
        while (i <= j) {
            while (values[i].compareTo(middle) < 0) {
                i++;
            }
            while (values[j].compareTo(middle) > 0) {
                j--;
            }
            if (i <= j) {
                swap(values, i++, j--);
            }
        }

        if (low < j)
            ascending(values, low, j);
        if (high > i)
            ascending(values, i, high);
    }

    private static <T extends Comparable> void descending(T[] values, int low, int high) {
        if (values.length == 0 || low >= high)
            return;
        int i = low, j = high;
        T middle = values[low + (high - low) / 2];
        while (i <= j) {
            while (values[i].compareTo(middle) > 0) {
                i++;
            }
            while (values[j].compareTo(middle) < 0) {
                j--;
            }
            if (i <= j) {
                swap(values, i++, j--);
            }
        }

        if (low < j)
            descending(values, low, j);
        if (high > i)
            descending(values, i, high);
    }

 }
