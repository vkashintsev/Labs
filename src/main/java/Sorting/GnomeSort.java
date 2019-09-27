package Sorting;

import java.util.List;

public class GnomeSort extends SortingAlgorithm {
    @Override
    public void sortAscending(List<Comparable> values) {
        int i = 0;
        while (i < values.size()) {
            if (i == 0 || values.get(i - 1).compareTo(values.get(i)) <= 0)
                i++;
            else {
                swap(values, i - 1, i);
                i--;
            }
        }
    }

    @Override
    public void sortDescending(List<Comparable> values) {
        int i = 0;
        while (i < values.size()) {
            if (i == 0 || values.get(i - 1).compareTo(values.get(i)) >= 0)
                i++;
            else {
                swap(values, i - 1, i);
                i--;
            }
        }
    }
}
