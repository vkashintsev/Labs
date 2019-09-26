import Cryptography.*;
import Sorting.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[])  {
        SortingAlgorithm sort = new QuickSort();
        ArrayList<Integer> arrayAsc = new ArrayList<>(Arrays.asList(1,4,3,2,6,7,8,4,2,1));
        ArrayList<Integer> arrayDesc = new ArrayList<>(Arrays.asList(1,4,3,2,6,7,8,4,2,1));
        sort.sortAscending(arrayAsc);
        sort.sortDescending(arrayDesc);
        SortingAlgorithm.printArray(arrayAsc);
        SortingAlgorithm.printArray(arrayDesc);
    }


}
