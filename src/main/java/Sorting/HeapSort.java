package Sorting;
import java.util.List;

public class HeapSort extends SortingAlgorithm {
    @Override
    public void sortAscending(List<Comparable> values) {
        heapSort(values, true);
    }

    @Override
    public void sortDescending(List<Comparable> values) {
        heapSort(values, false);
    }

    public  void heapSort(List<Comparable> values, boolean ascending) {
        for(int i = values.size()-1; i >= 0; i--)
            sink(values, i, values.size(), ascending);
        for(int i = values.size()-1; i > 0; i--){
            swap(values, 0, i);
            sink(values,0, i, ascending);
        }
    }
    static void sink(List<Comparable> values, int start, int end, boolean ascending){
        Comparable value = values.get(start);
        int i = start, next = 2*i+1;
        while(next < end) {
            if(next+1 < end && (!ascending ? -1 : 1) * values.get(next+1).compareTo(values.get(next)) > 0)
                next++;
            if((!ascending ? -1 : 1) * values.get(next).compareTo(value) <= 0)
                break;
            values.set(i, values.get(next));
            i = next;
            next = 2*i+1;
        }
        values.set(i, value);
    }
}
