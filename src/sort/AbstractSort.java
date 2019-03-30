package sort;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import myLib.utils.FileIO;

/**
 * リスト整列の抽象クラス。対象はComparableを実装
 *
 * @author tadaki
 * @param <T>
 */
public abstract class AbstractSort<T extends Comparable<T>> {

    public static final String LINE_SEPARATOR
            = System.getProperty("line.separator");
    protected List<T> data;//対象となるデータのリスト
    private int numSwap;//要素入替回数
    private int numComp;//要素比較回数
    private boolean counting = true;
    protected boolean debug = false;

    /**
     * コンストラクタ
     *
     * @param list
     */
    public AbstractSort(List<T> list) {
        data = Collections.synchronizedList(new LinkedList<>());
        list.stream().forEachOrdered(t -> data.add(t));
        numSwap = 0;
        numComp = 0;
    }

    public AbstractSort() {
    }

    /**
     * 対象となるデータを再設定
     *
     * @param list
     */
    public void setData(List<T> list) {
        data = Collections.synchronizedList(new LinkedList<>());
        list.stream().forEachOrdered(t -> data.add(t));
        numSwap = 0;
        numComp = 0;
    }

    /**
     * 整列を実行する抽象メソッド
     *
     * @return
     */
    abstract public List<T> doSort();

    /**
     * 大小関係 (vはwより小さい)
     *
     * @param v
     * @param w
     * @return
     */
    protected boolean less(T v, T w) {
        if (counting) {
            numComp++;
        }
        return (v.compareTo(w) < 0);
    }

    protected boolean lesseq(T v, T w) {
        if (counting) {
            numComp++;
        }
        return (v.compareTo(w) <= 0);
    }

    /**
     * 大小関係 (要素i は要素jより小さい)
     *
     * @param i
     * @param j
     * @return
     */
    protected boolean lessByIndex(int i, int j) {
        return less(data.get(i), data.get(j));
    }

    protected boolean lesseqByIndex(int i, int j) {
        return lesseq(data.get(i), data.get(j));
    }

    /**
     * 要素iとjを入れ替える
     *
     * @param i
     * @param j
     */
    protected void swap(int i, int j) {
        int n = data.size();
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Indexes are out of bound");
        }
        T t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
        if (counting) {
            numSwap++;
        }
    }

    /**
     * 位置iの要素を位置jの前へ挿入
     *
     * @param i
     * @param j
     */
    protected void insert(int i, int j) {
        T t = data.remove(i);
        data.add(j, t);
    }

    /**
     * 整列されているかを判定
     *
     * @return
     */
    public boolean isSorted() {
        counting = false;
        boolean b = true;
        for (int i = 0; i < data.size() - 1 && b; i++) {
            b = b & lesseqByIndex(i, i + 1);
        }
        counting = true;
        return b;
    }

    /**
     * リストを文字列化
     *
     * @return 
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        data.stream().forEachOrdered(d -> sb.append(d).append(LINE_SEPARATOR));
        return sb.toString();
    }

    public List<T> getList() {
        return data;
    }

    public int getNumSwap() {
        return numSwap;
    }

    public int getNumCompare() {
        return numComp;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * テストランを実施
     *
     * @param sort
     * @throws IOException
     */
    static public void testRun(AbstractSort sort)
            throws IOException {
        sort.doSort();
        if (sort.isSorted()) {
            System.out.println("Sorting Completes");
            System.out.println("Number of Compare " + sort.getNumCompare());
            System.out.println("Number of Exchange " + sort.getNumSwap());
            try (BufferedWriter out = FileIO.openWriter("output.txt")) {
                out.append(sort.printList());
            }
        } else {
            System.out.println("Sorting fails");
        }
    }

    static public void testStable(AbstractSort<Data> sort) {
        List<Data> data = sort.doSort();
        System.out.println(sort.printList());
        if (sort.isSorted()) {
            boolean b = true;
            for (int i = 0; i < data.size() - 1 && b; i++) {
                Data d0 = data.get(i);
                Data d1 = data.get(i + 1);
                if (d0.compareTo(d1) == 0) {
                    if (Integer.valueOf(d0.label).compareTo(
                            Integer.valueOf(d1.label)) > 0) {
                        System.out.println(data.get(i) + ":" + data.get(i + 1));
                        b = false;
                    }
                }
            }
            if (b) {
                System.out.println("stable");
            } else {
                System.out.println("unstable");
            }
        } else {
            System.out.println("something wrong");
        }

    }
}
