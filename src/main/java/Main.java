import Sorting.QuickSort;
import Sorting.ShakerSort;
import Sorting.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String args[])  {
        SortingAlgorithm sort = new ShakerSort();
        ArrayList<Integer> arrayAsc = new ArrayList<>(Arrays.asList(1,4,3,2,6,7,8,4,2,1));
        ArrayList<Integer> arrayDesc = new ArrayList<>(Arrays.asList(1,4,3,2,6,7,8,4,2,1));
        sort.sortAscending(arrayAsc);
        sort.sortDescending(arrayDesc);
        SortingAlgorithm.printArray(arrayAsc);
        SortingAlgorithm.printArray(arrayDesc);
    }


}
