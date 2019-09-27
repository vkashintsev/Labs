import DataStructure.BinaryTree;
import DataStructure.TreeVisitor;
import Sorting.*;
import Sorting.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String args[])  {
        SortingAlgorithm sort = new TreeSort();
        ArrayList<Comparable> arrayAsc = new ArrayList<>(Arrays.asList(1,4,3,2,6,7,8,4,2,1));
        ArrayList<Comparable> arrayDesc = new ArrayList<>(Arrays.asList(1,4,3,2,6,7,8,4,2,1));
        sort.sortAscending(arrayAsc);
        sort.sortDescending(arrayDesc);
        SortingAlgorithm.printArray(arrayAsc);
        SortingAlgorithm.printArray(arrayDesc);
    }


}
