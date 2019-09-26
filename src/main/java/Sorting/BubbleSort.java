package Sorting;

public class BubbleSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable> void sortAscending(T[] values) {
        boolean flag = true;
        int right = values.length;
        while (flag) {
            flag = false;
            for (int i = 0; i + 1 < right; i++) {
                if (values[i].compareTo(values[i + 1])>0) {
                    swap(values, i, i + 1);
                    flag = true;
                }
            }
            right--;
        }
    }

    @Override
    public <T extends Comparable> void sortDescending(T[] values) {
        boolean flag = true;
        int right = values.length;
        while (flag) {
            flag = false;
            for (int i = 0; i + 1 < right; i++) {
                if (values[i].compareTo(values[i + 1])<0) {
                    swap(values, i, i + 1);
                    flag = true;
                }
            }
            right--;
        }
    }
}
