package sort;

import java.io.IOException;
import java.util.List;
import static sort.AbstractSort.testRun;

/**
 * Quick Sort
 *
 * @author tadaki
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>>
        extends AbstractSort<T> {

    public QuickSort(List<T> data) {
        super(data);
    }

    public QuickSort() {
    }

    /**
     * sort の実装
     *
     * @return
     */
    @Override
    public List<T> doSort() {
        sortSub(0, data.size() - 1);
        return data;
    }

    /**
     * sortの実態：範囲を指定
     *
     * @param left
     * @param right
     */
    protected void sortSub(int left, int right) {
        if (right <= left) {
            return;
        }
        int boundary = partition(left, right);
        sortSub(left, boundary - 1);
        sortSub(boundary + 1, right);
    }

    /**
     * 分配作業：右端の要素よりも小さいものが戻り値よりも先に、
     *
     * 右端の要素よりも大きいものが戻り値よりも後になる
     *
     * @param left 範囲の左端
     * @param right 範囲の右端
     * @return 右端の要素が移動した先のインデクス
     */
    protected int partition(int left, int right) {
        int fromLeft = left - 1;
        int fromRight = right;
        T v = data.get(right);

        for (;;) {
            while (less(data.get(++fromLeft), v)) {
            }
            while (less(v, data.get(--fromRight))) {
                if (fromRight == left) {
                    break;
                }
            }
            if (fromLeft >= fromRight) {
                break;
            }
            swap(fromLeft, fromRight);
        }
        swap(fromLeft, right);
        return fromLeft;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static public void main(String args[]) throws IOException {
        int numData = 100;
        List<Data> data = Data.createData(numData);
        testRun(new QuickSort<>(data));
        System.out.println("test stability");
        List<Data> data2 = Data.createData(100, 0.5);
        testStable(new InsertionSort<>(data2));
    }
}
