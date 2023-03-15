package sort;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class for sorting.  Targes are instances of Comparable
 *
 * @author tadaki
 * @param <T>
 */
public abstract class AbstractSort<T extends Comparable<T>> {

    public static final String LINE_SEPARATOR
            = System.getProperty("line.separator");
    protected List<T> data;//Target data list
    private int numSwap;//The number of exchanging elements
    private int numComp;//The number of comparing elements
    private boolean counting = true;
    protected boolean debug = false;

    /**
     * Constructor
     *
     * @param list
     */
    public AbstractSort(List<T> list) {
        data = Collections.synchronizedList(new LinkedList<>());
        data.addAll(list);
        numSwap = 0;
        numComp = 0;
    }

    public AbstractSort() {
    }

    /**
     * Resetting data
     *
     * @param list
     */
    public void setData(List<T> list) {
        data = Collections.synchronizedList(new LinkedList<>());
        data.addAll(list);
        numSwap = 0;
        numComp = 0;
    }

    /**
     * Abstract method for sorting
     *
     * @return
     */
    abstract public List<T> doSort();

    /**
     * v is less than w
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
     * i-th element  less than j-th element
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
     * swap i-th element with j-th element
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
     * insert j-th element before i-th element
     *
     * @param i
     * @param j
     */
    protected void insert(int i, int j) {
        T t = data.remove(i);
        data.add(j, t);
    }

    /**
     * return true if the data is sorted
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
     * convert data into string
     *
     * @return 
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        data.forEach(d -> sb.append(d).append(LINE_SEPARATOR));
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

    public int getSize(){
        return data.size();
    }
    
    /**
     * test run
     *
     * @param sort
     * @throws IOException
     */
    static public void testRun(AbstractSort sort)
            throws IOException {
        int numElement=sort.getSize();
        sort.doSort();
        if(sort.getSize()!=numElement){
            System.err.println("Num Element changes");
            System.exit(-1);
        }
        if (sort.isSorted()) {
            System.out.println("Sorting Completes");
            System.out.println("Number of Compare " + sort.getNumCompare());
            System.out.println("Number of Exchange " + sort.getNumSwap());
            try (PrintStream out = new PrintStream("output.txt")) {
                out.println(sort.printList());
            }
        } else {
            System.out.println("Sorting fails");
        }
    }

    /**
     * testing stability of result
     * 
    * @param sort 
     */
    static public void testStable(AbstractSort<Data> sort) {
        List<Data> data = sort.doSort();
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
