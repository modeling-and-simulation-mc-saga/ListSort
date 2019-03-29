package sort;

import java.io.IOException;
import java.util.List;
import myLib.utils.Utils;

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

    protected void sortSub(int left, int right) {
        if (right <= left) {
            throw new IllegalArgumentException("illegal range");
        }
        if (right == left + 1) {
            return;
        }
        int middle = (right + left) / 2;
        sortSub(left, middle);
        sortSub(middle, right);
        List<T> tmpList = mergeList(left, middle, right);
        for (int p = 0; p < tmpList.size(); p++) {
            data.set(left + p, tmpList.get(p));
        }

    }

    private List<T> mergeList(int left, int middle, int right) {
        List<T> tmp = Utils.createList();
        int leftIndex = left;
        int rightIndex = middle;
        while (leftIndex < middle || rightIndex < right) {
            if (leftIndex >= middle) {//左側終了
                for (int k = rightIndex; k < right; k++) {
                    tmp.add(data.get(k));
                }
                return tmp;
            }
            if (rightIndex >= right) {//右側終了
                for (int k = leftIndex; k < middle; k++) {
                    tmp.add(data.get(k));
                }
                return tmp;
            }
            if (lessByIndex(leftIndex, rightIndex)) {
                tmp.add(data.get(leftIndex));
                leftIndex++;
            } else {
                tmp.add(data.get(rightIndex));
                rightIndex++;
            }
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

    }

}
