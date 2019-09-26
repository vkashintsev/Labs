package Sorting;

import java.util.List;

public class SelectionSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable> void sortAscending(List<T> values) {
        for (int i = 0; i < values.size(); i++) {
            T minValue = values.get(i);
            int minIndex = i;
            for (int j = i + 1; j < values.size(); j++){
                if (values.get(j).compareTo(minValue) < 0) {
                    minValue = values.get(j);
                    minIndex = j;
                }
            }
            swap(values, i, minIndex);
        }
    }

    @Override
    public <T extends Comparable> void sortDescending(List<T> values) {
        for (int i = 0; i < values.size(); i++) {
            T minValue = values.get(i);
            int minIndex = i;
            for (int j = i + 1; j < values.size(); j++){
                if (values.get(j).compareTo(minValue) > 0) {
                    minValue = values.get(j);
                    minIndex = j;
                }
            }
            swap(values, i, minIndex);
        }
    }
}
