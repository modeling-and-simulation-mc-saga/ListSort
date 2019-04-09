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
        for (int i = 1; i < n; i++) {
            int k = findPosition(0, i, i);
            if (k != i) {
                insert(i, k);
            }
        }
        return data;
    }

    /**
     * 要素kを挿入する位置を調べるために、要素kよりも小さい要素を前方に向かって探す
     *
     * @param from 範囲の下限
     * @param to 範囲の上限（toは含まれない）
     * @param k
     * @return 発見した位置、なかった場合はfromを返す
     */
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
        List<Data> data2 = Data.createData(100, 0.5);
        testStable(new InsertionSort<>(data2));
    }

}
