package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sample data class for sorting
 *
 * Instance of Comparable
 *
 * @author tadaki
 */
public class Data implements Comparable<Data> {

    //Fields are immutable
    public final String label;
    private final int value;

    /**
     * Constructor: creating an instance with label and value
     *
     * @param label
     * @param value
     */
    public Data(String label, int value) {
        //substitute arguments into fields
        this.label = label;
        this.value = value;
    }

    /**
     * comparing with other instance of the same class
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Data o) {
        return this.value - o.value;
    }

    /**
     * convert this instance into string
     *
     * @return
     */
    @Override
    public String toString() {
        return label + ":" + value;
    }

    /**
     * generating test data
     *
     * @param numData the number of elements
     * @return
     */
    static public List<Data> createData(int numData) {
        return createData(numData, 10.);
    }

    static public List<Data> createData(int numData, double p) {
        return createData(numData, p, new Random(48L));
    }

    static public List<Data> createData(int numData, double p, Random random) {
        List<Data> data = new ArrayList<>();
        for (int i = 0; i < numData; i++) {
            int k = (int) (p * numData * random.nextDouble());
            data.add(new Data(String.valueOf(i), k));
        }
        return data;
    }
}
