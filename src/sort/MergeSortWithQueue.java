package sort;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import myLib.utils.Utils;
import static sort.AbstractSort.testRun;

/**
 * MergeSortを非再帰的に実行：再帰アルゴリズムとメソッドを共有
 *
 * @author tadaki
 * @param <T> 整列対象のクラス
 */
public class MergeSortWithQueue<T extends Comparable<T>> extends MergeSort<T> {

    public MergeSortWithQueue(List<T> data) {
        super(data);
    }

    public MergeSortWithQueue() {
        super();
    }

    @Override
    protected void sortSub(int begin, int end) {
        //引数が正しくない場合は例外を発生
        if (begin != 0 || end != data.size()) {
            throw new IllegalArgumentException("sortSubの引数が不正");
        }
        Queue<List<T>> queue = new ConcurrentLinkedQueue<>();
        //全ての要素をリストにして待ち行列へ
        for (int i = begin; i < end; i++) {
            List<T> tt = Utils.createList();
            tt.add(data.get(i));
            queue.add(tt);
        }
        //二つ毎にマージ
        while (queue.size() > 1) {
            mergeListWithQueue(queue);
        }
        List<T> listOut = queue.poll();
        for (int i = 0; i < data.size(); i++) {
            data.set(begin + i, listOut.get(i));
        }
    }

    protected void mergeListWithQueue(Queue<List<T>> queue) {
        List<T> t1 = queue.poll();
        List<T> t2 = queue.poll();
        List<T> tOut = mergeList(t1, t2);
        queue.add(tOut);
    }

    /**
     * 二つのリストのmerge
     *
     * @param a
     * @param b
     * @return
     */
    protected List<T> mergeList(List<T> a, List<T> b) {
        List<T> c = Utils.createList();
        while ((!a.isEmpty()) || (!b.isEmpty())) {
            int k = checkEmptynessTwoLists(a, b);
            T t = null;
            switch (k) {
                case 3://(!a.isEmpty()) && (!b.isEmpty())
                    if (less(a.get(0), b.get(0))) {
                        t = a.remove(0);
                    } else {
                        t = b.remove(0);
                    }
                    break;
                case 2://(!a.isEmpty()) && (b.isEmpty())
                    c.addAll(a);
                    return c;
                case 1://(a.isEmpty()) && (!b.isEmpty())
                    c.addAll(b);
                    return c;
                default:
            }
            c.add(t);
        }
        return c;
    }

    /**
     * 二つのリストが空であるか否かを確認
     *
     * @param <T>
     * @param a
     * @param b
     * @return 0:両方が空、 1:aは空、bは空でない、2:aは空でない、bは空、
     *
     * 3:両方が空でない
     */
    private int checkEmptynessTwoLists(List<T> a, List<T> b) {
        int k = 0;
        if (!a.isEmpty()) {
            k += 2;
        }
        if (!b.isEmpty()) {
            k += 1;
        }
        return k;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static public void main(String args[]) throws IOException {
        int numData = 100;
        List<Data> data = Data.createData(numData);
        testRun(new MergeSortWithQueue<>(data));
    }
}
