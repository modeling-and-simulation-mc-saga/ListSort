package sort;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author tadaki
 * @param <T>
 */
public class BinaryInsertionSort<T extends Comparable<T>>
        extends InsertionSort<T> {

    public BinaryInsertionSort(List<T> data) {
        super(data);
    }

    public BinaryInsertionSort() {
    }

    @Override
    protected int findPosition(int from, int to, int k) {
        if (to == from + 1) {
            if (lesseqByIndex(from, k)) {
                return from + 1;
            }
            return from;
        }
        int m = (int) ((to + from) / 2.);
        if (lessByIndex(k, m)) {
            return findPosition(from, m, k);
        } else {
            return findPosition(m, to, k);
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static public void main(String args[]) throws IOException {
        int numData = 1000;
        List<Data> data = Data.createData(numData);
        testRun(new BinaryInsertionSort<>(data));
        System.out.println("test stability");
        List<Data> data2 = Data.createData(100, 0.5);
        testStable(new InsertionSort<>(data2));
    }

}
