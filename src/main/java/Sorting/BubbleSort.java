package Sorting;

import java.util.List;

public class BubbleSort extends SortingAlgorithm {
    @Override
    public void sortAscending(List<Comparable> values) {
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
    public void sortDescending(List<Comparable> values) {
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
