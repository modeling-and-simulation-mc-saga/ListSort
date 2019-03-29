package sort;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tadaki
 * @param <T>
 */
public class InsertionSort<T extends Comparable<T>>
        extends AbstractSort<T> {

    public InsertionSort(List<T> data) {
        super(data);
    }

    public InsertionSort() {
    }

    @Override
    public List<T> doSort() {
        int n = data.size();
        for (int i = 1; i < n; i++) {
            int k = findPosition(0, i, i);
            if (k != i) {
                insert(i, k);
            }
            /*
            for (int j = i; j > 0 && lessByIndex(j, j - 1); j--) {
                swap(j, j - 1);
            }
             */
        }
        return data;
    }

    protected int findPosition(int from, int to, int k) {
        for (int i = to - 1; i >= from; i--) {
            if (lesseqByIndex(i, k)) {
                return i + 1;
            }
        }
        return from;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static public void main(String args[]) throws IOException {
        int numData = 1000;
        List<Data> data = Data.createData(numData);

        testRun(new InsertionSort<>(data));
        System.out.println("test stability");
        List<Data> data2 = Data.createData(100,0.5);
        testStable(new InsertionSort<>(data2));
    }

}
