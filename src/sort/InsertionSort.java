package sort;

import java.io.IOException;
import java.util.List;

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

    /**
     * sort の実装
     *
     * @return
     */
    @Override
    public List<T> doSort() {
        int n = data.size();






        return data;
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
        List<Data> data2 = Data.createData(100, 0.5);
        testStable(new InsertionSort<>(data2));
    }

}
