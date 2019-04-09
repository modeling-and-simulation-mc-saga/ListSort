package sort;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author tadaki
 * @param <T>
 */
public class BubbleSort<T extends Comparable<T>>
        extends AbstractSort<T> {

    public BubbleSort(List<T> data) {
        super(data);
    }

    public BubbleSort() {
    }

    /**
     * sort の実装
     * @return 
     */
    @Override
    public List<T> doSort() {
        int n = data.size();
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (lessByIndex(j + 1, j)) {
                    swap(j + 1, j);
                }
            }
        }
        return data;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static public void main(String args[]) throws IOException {
        int numData = 100;
        List<Data> data = Data.createData(numData);
        testRun(new BubbleSort<>(data));
                System.out.println("test stability");
        List<Data> data2 = Data.createData(100, 0.5);
        testStable(new InsertionSort<>(data2));

    }

}
