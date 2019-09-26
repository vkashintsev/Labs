import Cryptography.*;
import Sorting.BubbleSort;
import Sorting.InsertionSort;
import Sorting.QuickSort;

public class Main {
    public static void main(String args[])  {
        QuickSort sort = new QuickSort();
        Integer[] array = new Integer[]{1,4,3,2,6,7,8,4,2,1};
        sort.sortDescending(array);
        BubbleSort.printArray(array);
    }


}
