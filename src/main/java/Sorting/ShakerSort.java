package Sorting;

import java.util.List;

public class ShakerSort extends SortingAlgorithm {
    @Override
    public void sortAscending(List<Comparable> values) {
        int left=0;
        int right=values.size()-1;
        while (left < right) {
            for (int i=left; i<right;i++) {
                if (values.get(i).compareTo(values.get(i+1)) > 0) {
                    swap(values, i, i+1);
                }
            }
            right--;
            for (int i=right; i>left; i--) {
                if (values.get(i).compareTo(values.get(i-1)) < 0) {
                    swap(values, i, i-1);
                }
            }
            left++;
        }
    }

    @Override
    public void sortDescending(List<Comparable> values) {
        int left=0;
        int right=values.size()-1;
        while (left < right) {
            for (int i=left; i<right;i++) {
                if (values.get(i).compareTo(values.get(i+1)) < 0) {
                    swap(values, i, i+1);
                }
            }
            right--;
            for (int i=right; i>left; i--) {
                if (values.get(i).compareTo(values.get(i-1)) > 0) {
                    swap(values, i, i-1);
                }
            }
            left++;
        }
    }
}
