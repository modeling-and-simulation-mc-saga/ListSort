package sort;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author tadaki
 * @param <T>
 */
public class QuickSortWithStack<T extends Comparable<T>>
        extends QuickSort<T> {

    class Range {//対象範囲を表すクラス

        int begin, end;

        Range(int from, int to) {
            this.begin = from;
            this.end = to;
        }

        public int getBegin() {
            return begin;
        }

        public int getEnd() {
            return end;
        }

    }

    public QuickSortWithStack(List<T> data) {
        super(data);
    }

    public QuickSortWithStack() {
    }

    @Override
    protected void sortSub(int left, int right) {
        Stack<Range> rangeStack = new Stack<>();
        rangeStack.push(new Range(left, right));
        while (!rangeStack.isEmpty()) {
            if (debug) {
                System.out.println("stack:" + showStack(rangeStack));
            }
            Range targetRange = rangeStack.pop();
            int begin = targetRange.begin;
            int end = targetRange.end;
            if (end <= begin) {
                continue;
            }
            int boundary = partition(begin, end);
            int leftLength = boundary - begin;
            int rightLength = end - boundary;
            //長いほうを先にプッシュ
            //長さが1以下のものはプッシュしない
            if (leftLength > rightLength) {
                if (leftLength > 1) {
                    rangeStack.push(new Range(begin, boundary - 1));
                }
            }
            if (rightLength > 1) {
                rangeStack.push(new Range(boundary + 1, end));
            }
            if (leftLength <= rightLength) {
                if (leftLength > 1) {
                    rangeStack.push(new Range(begin, boundary - 1));
                }
            }
        }
    }

    /**
     * スタックの中を表示
     *
     * @param rangeStack
     * @return
     */
    private String showStack(Stack<Range> rangeStack) {
        StringBuilder sb = new StringBuilder();
        rangeStack.stream().forEachOrdered(range -> {
            int begin = range.getBegin();
            int end = range.getEnd();
            sb.append("(");
            for (int i = begin; i <= end; i++) {
                sb.append(data.get(i)).append(" ");
            }
            int index = sb.length() - 1;
            if (index >= 0 && sb.charAt(index) == ' ') {
                //最後のスペースを削除
                sb.deleteCharAt(index);
            }
            sb.append(")");
        });
        return sb.toString();
    }
}
