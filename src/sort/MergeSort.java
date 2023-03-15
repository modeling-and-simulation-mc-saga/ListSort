package sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tadaki
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

    public MergeSort(List<T> data) {
        super(data);
    }

    public MergeSort() {
        super();
    }

    @Override
    public List<T> doSort() {
        sortSub(0, data.size());
        return data;
    }

    /**
     * implementing sort: specifying area by indexes of elements
     *
     * @param left
     * @param right
     */
    protected void sortSub(int left, int right) {
        if (right <= left) {
            throw new IllegalArgumentException("illegal range");
        }
        if (right == left + 1) {
            return;
        }
        //devide list into two sub-list
        int middle = (right + left) / 2;
        sortSub(left, middle);
        sortSub(middle, right);
        //merge two sorted lists
        List<T> tmpList = mergeList(left, middle, right);
        for (int p = 0; p < tmpList.size(); p++) {
            data.set(left + p, tmpList.get(p));
        }

    }

    /**
     * merge two sorted lists
     *
     * @param left
     * @param middle
     * @param right
     * @return
     */
    private List<T> mergeList(int left, int middle, int right) {
        List<T> tmp = new ArrayList<>();
        int leftIndex = left;
        int rightIndex = middle;
        while (leftIndex < middle && rightIndex < right) {
            if (lessByIndex(leftIndex, rightIndex)) {
                tmp.add(data.get(leftIndex));
                leftIndex++;
            } else {
                tmp.add(data.get(rightIndex));
                rightIndex++;
            }
        }
        //appending remaining elements in left part
        for (int k = rightIndex; k < right; k++) {
            tmp.add(data.get(k));
        }
        //appending remaining elements in right part
        for (int k = leftIndex; k < middle; k++) {
            tmp.add(data.get(k));
        }
        return tmp;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static public void main(String args[]) throws IOException {
        int numData = 100;
        List<Data> data = Data.createData(numData);
        testRun(new MergeSort<>(data));
        List<Data> data2 = Data.createData(100, 0.5);
        testStable(new MergeSort<>(data2));
    }

}
