package Sorting;

import java.util.List;

public class ShellSort extends SortingAlgorithm {
    @Override
    public void sortAscending(List<Comparable> values) {
        int n = values.size();
        for (int middle = n / 2; middle > 0; middle /= 2) {
            for (int i = middle; i < n; i++) {
                Comparable key = values.get(i);
                int j = i;
                while (j >= middle && values.get(j - middle).compareTo(key) > 0) {
                    values.set(j, values.get(j-middle));
                    j -= middle;
                }
                values.set(j, key);
            }
        }
    }

    @Override
    public void sortDescending(List<Comparable> values) {
        int n = values.size();
        for (int middle = n / 2; middle > 0; middle /= 2) {
            for (int i = middle; i < n; i++) {
                Comparable key = values.get(i);
                int j = i;
                while (j >= middle && values.get(j - middle).compareTo(key) < 0) {
                    values.set(j, values.get(j-middle));
                    j -= middle;
                }
                values.set(j, key);
            }
        }
    }
}
