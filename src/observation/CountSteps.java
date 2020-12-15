package observation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.*;
import sort.*;

/**
 *
 * @author tadaki
 */
public class CountSteps {

    private final AbstractSort<Data> sort;

    public CountSteps(AbstractSort<Data> sort) {
        this.sort = sort;
    }

    public void setRandomDataAndDo(int numData) {
        List<Data> data = Data.createData(numData);
        sort.setData(data);
        sort.doSort();
    }

    public List<PerformanceData> measure(int min, int max) {
        int n = min;
        List<PerformanceData> list = Utils.createList();
        while (n <= max) {
            setRandomDataAndDo(n);
            list.add(new PerformanceData(n,
                    sort.getNumCompare(), sort.getNumSwap()));
            n *= 2;
        }
        return list;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        List<AbstractSort<Data>> sorts = Utils.createList();
        sorts.add(new BubbleSort<>());
        sorts.add(new InsertionSort<>());
        sorts.add(new SelectionSort<>());
        sorts.add(new MergeSort<>());
        sorts.add(new QuickSort<>());

        for (int i = 0; i < sorts.size(); i++) {
            AbstractSort<Data> sort = sorts.get(i);
            CountSteps cs = new CountSteps(sort);
            String filename = sort.getClass().getSimpleName() + ".txt";
            List<PerformanceData> list = cs.measure(16, 1024);
            try (BufferedWriter out = FileIO.openWriter(filename)) {
                for (PerformanceData p : list) {
                    FileIO.writeSSV(out, p.n, p.numComp, p.numExch);
                }
            }
        }

    }

}
