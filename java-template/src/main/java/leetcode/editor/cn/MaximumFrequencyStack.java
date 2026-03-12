package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MaximumFrequencyStack {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * freqToVals
     * [5] push(5)
     * freq=1 [5]
     * [5,7] push(7)
     * freq=1 [5,7]
     * [5,7,5] push(5)
     * freq=2 [5]
     * freq=1 [5,7]
     * [5,7,5,7] push(7)
     * freq=2 [5,7]
     * freq=1 [5,7]
     * [5,7,5,7,4] push(4)
     * freq=2 [5,7]
     * freq=1 [5,7,4]
     * [5,7,5,7,4,5] push(5)
     * freq=3 [5]
     * freq=2 [5,7]
     * freq=1 [5,7,4]
     * [5,7,5,7,4] pop()
     * freq=3 []
     * freq=2 [5,7]
     * freq=1 [5,7,4]
     * [5,7,5,4] pop()
     * freq=3 []
     * freq=2 [5]
     * freq=1 [5,7,4]
     * [5,7,4] pop()
     * freq=3 []
     * freq=2 []
     * freq=1 [5,7,4]
     * [5,7] pop()
     * freq=3 []
     * freq=2 []
     * freq=1 [5,7]
     * [5] pop()
     * freq=3 []
     * freq=2 []
     * freq=1 [5]
     * [] pop()
     * freq=3 []
     * freq=2 []
     * freq=1 []
     *
     */
    class FreqStack {
        Map<Integer, Integer> valToFreq;
        Map<Integer, Deque<Integer>> freqToVals;
        int maxFreq;

        public FreqStack() {
            valToFreq = new HashMap<>();
            freqToVals = new HashMap<>();
            maxFreq = 0;
        }

        public void push(int val) {
            // 修改VF表:val对应的freq+1
            Integer freq = valToFreq.getOrDefault(val, 0) + 1;
            valToFreq.put(val, freq);
            // 修改FV表:freq对应的vals中添加val
            freqToVals.putIfAbsent(freq, new ArrayDeque<>());
            freqToVals.get(freq).push(val);
            // 更新maxFreq
            maxFreq = Math.max(maxFreq, freq);
        }

        /**
         * 没必要在freqToVals中删除vals为空的maxFreq
         * 没必要在valToFreq中删除freq为0的val
         */
        public int pop() {
            // 1. 获取maxFreq对应的vals
            Deque<Integer> vals = freqToVals.get(maxFreq);
            // 2. 获取要删除的val
            Integer val = vals.pop();
            // 3. 如果vals为空，更新maxFreq
            if (vals.isEmpty())
                maxFreq--;
            // 4. 修改VF表，val对应的freq - 1
            valToFreq.put(val, valToFreq.get(val) - 1);
            // 5. 返回val
            return val;
        }
    }

    /**
     * Your FreqStack object will be instantiated and called as such:
     * FreqStack obj = new FreqStack();
     * obj.push(val);
     * int param_2 = obj.pop();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        FreqStack solution = new MaximumFrequencyStack().new FreqStack();
        // put your test code here

    }
}