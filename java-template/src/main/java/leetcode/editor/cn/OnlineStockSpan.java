package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class OnlineStockSpan {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：单调栈寻找上一个更大的元素
     * 1. 上一个：正序遍历
     * 2. 更大的元素：弹出小于等于当前元素的栈顶元素
     * 3. 最大连续日数：记录索引做差为结果
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    class StockSpanner {
        ArrayDeque<Integer> stk;
        List<Integer> prices;

        public StockSpanner() {
            stk = new ArrayDeque<>();
            prices = new ArrayList<>();
        }

        public int next(int price) {
            prices.add(price);
            while (!stk.isEmpty() && prices.get(stk.peek()) <= price)
                stk.pop();
            int index = prices.size() - 1;
            int ans = index - (stk.isEmpty() ? -1 : stk.peek());
            stk.push(index);
            return ans;
        }
    }

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        StockSpanner solution = new OnlineStockSpan().new StockSpanner();
        // put your test code here

    }
}