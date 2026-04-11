package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaysToAddParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：分治
     * 1. 函数定义：diffWaysToCompute可以计算expression的所有组合结果
     * 2. 分：给某一个运算符左右加括号，将expression分为两个子表达式
     * 3. 治：用diffWaysToCompute递归计算左右两个子表达式的所有结果
     */
    class Solution {
        // 备忘录
        Map<String, List<Integer>> memo = new HashMap<>();

        public List<Integer> diffWaysToCompute(String expression) {
            // 查备忘录
            if (memo.containsKey(expression))
                return memo.get(expression);
            // 记录当前expression的所有结果
            List<Integer> res = new ArrayList<>();
            // 遍历expression
            for (int i = 0; i < expression.length(); i++) {
                // 获取字符
                char c = expression.charAt(i);
                // 运算符左右两边的表达式作为子表达式
                if (c == '-' || c == '*' || c == '+') {
                    // 计算左子表达式的所有可能结果
                    List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                    // 计算右子表达式的所有可能结果
                    List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                    // 根据运算符组合左右子表达式的所有可能结果
                    for (int a : left) {
                        for (int b : right) {
                            if (c == '+') res.add(a + b);
                            else if (c == '-') res.add(a - b);
                            else res.add(a * b);
                        }
                    }
                }
            }
            // 若res为空,则代表expression只有一个数字,没有运算符
            if (res.isEmpty())
                res.add(Integer.parseInt(expression));
            // 结果添加备忘录
            memo.put(expression, res);
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new DifferentWaysToAddParentheses().new Solution();
        // put your test code here

    }
}