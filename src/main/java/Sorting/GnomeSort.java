package Sorting;

import java.util.List;

public class GnomeSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable> void sortAscending(List<T> values) {
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
    public <T extends Comparable> void sortDescending(List<T> values) {
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
