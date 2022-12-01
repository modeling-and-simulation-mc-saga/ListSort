package observation;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import sort.*;

/**
 *
 * @author tadaki
 */

public class CountSteps{


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
        List<PerformanceData> list = new ArrayList<>();
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
        List<AbstractSort<Data>> sorts = new ArrayList<>();
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
            try (PrintStream out = new PrintStream(filename)) {
                list.forEach(p -> 
                        out.println(p.n() + " " + p.numComp() + " " + p.numExch())
                );
            }
        }

    }

}
