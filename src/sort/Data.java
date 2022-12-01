package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 整列アルゴリズム用のサンプルデータクラス
 *
 * Comparableとすることで、比較可能であることを示す
 *
 * @author tadaki
 */
public class Data implements Comparable<Data> {

    //finalとすることで、変更不可とする
    public final String label;
    private final int value;

    /**
     * コンストラクタ：ラベルと値を与えてインスタンスを生成
     *
     * @param label
     * @param value
     */
    public Data(String label, int value) {
        //引数の値をインスタンス(this)へ代入
        this.label = label;
        this.value = value;
    }

    /**
     * 同じクラスの他のインスタンスと比較
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Data o) {
        return this.value - o.value;
    }

    /**
     * インスタンスを文字列で表現
     *
     * @return
     */
    @Override
    public String toString() {
        return label + ":" + value;
    }

    /**
     * テストデータの生成
     *
     * @param numData データ数
     * @return テストデータをリストで返す
     */
     static public List<Data> createData(int numData){
         return createData(numData,10.);
     }
    static public List<Data> createData(int numData,double p) {
        List<Data> data = new ArrayList<>();
        for (int i = 0; i < numData; i++) {
            int k = (int) (p * numData * Math.random());
            data.add(new Data(String.valueOf(i), k));
        }
        return data;
    }
}
