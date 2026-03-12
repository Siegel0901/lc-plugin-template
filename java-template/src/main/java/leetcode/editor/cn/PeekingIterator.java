package leetcode.editor.cn;

import java.util.Iterator;

public class PeekingIterator {

    //leetcode submit region begin(Prohibit modification and deletion)
    // Java Iterator interface reference:
    // https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iter;
        // 缓存迭代器中的下一个元素
        private Integer nextElem;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            iter = iterator;
            nextElem = iterator.next();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return nextElem;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer res = nextElem;
            if (iter.hasNext())
                nextElem = iter.next();
            else
                nextElem = null;
            return res;
        }

        @Override
        public boolean hasNext() {
            return nextElem != null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // put your test code here

    }
}