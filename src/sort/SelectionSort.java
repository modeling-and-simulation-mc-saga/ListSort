package sort;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author tadaki
 * @param <T>
 */
public class SelectionSort<T extends Comparable<T>>
        extends AbstractSort<T> {

    public SelectionSort(List<T> data) {
        super(data);
    }

    public SelectionSort() {
    }

    /**
     * sort の実装
     *
     * @return
     */
    @Override
    public List<T> doSort() {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int m = findMinimum(i, n);
            if (m != i) {
                swap(i, m);
            }
        }
        return data;
    }

    /**
     * find index of minimum element in array[j])(from<=i<to)
     *
     * @param from
     * @param to
     * @return
     */
    private int findMinimum(int from, int to) {
        int m = from;
        for (int j = from + 1; j < to; j++) {
            if (lessByIndex(j, m)) {
                m = j;
            }
        }
        return m;
    }

    static public void main(String args[]) throws IOException {
        int numData = 100;
        List<Data> data = Data.createData(numData);
        testRun(new SelectionSort<>(data));
        System.out.println("test stability");
        List<Data> data2 = Data.createData(100, 0.5);
        testStable(new SelectionSort<>(data2));
    }
}
