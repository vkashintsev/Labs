package Sorting;

import java.util.List;

public class BubbleSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable> void sortAscending(List<T> values) {
        boolean flag = true;
        int right = values.size();
        while (flag) {
            flag = false;
            for (int i = 0; i + 1 < right; i++) {
                if (values.get(i).compareTo(values.get(i+1))>0) {
                    swap(values, i, i + 1);
                    flag = true;
                }
            }
            right--;
        }
    }

    @Override
    public <T extends Comparable> void sortDescending(List<T> values) {
        boolean flag = true;
        int right = values.size();
        while (flag) {
            flag = false;
            for (int i = 0; i + 1 < right; i++) {
                if (values.get(i).compareTo(values.get(i+1))<0) {
                    swap(values, i, i + 1);
                    flag = true;
                }
            }
            right--;
        }
    }
}
