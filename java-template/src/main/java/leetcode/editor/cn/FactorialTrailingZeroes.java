package leetcode.editor.cn;

public class FactorialTrailingZeroes {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：计算 n! 中因子 5 的个数
     * 公式：n/5 + n/25 + n/125 + ...
     * 证明：
     * 1. 阶乘过程中，只有出现了因子2和因子5才会有尾随零，因为2*5=10
     * 2. 由于因子2比因子5多，故尾随零的数量 = min(因子2的数量，因子5的数量) = 因子5的数量
     * 3. 为什么公式是n/5 + n/25 + n/125 + ...？
     * 3.1. n/5统计有1个因子5的数：5 10 15 20 25 30,次数+1
     * 3.2. n/25统计有2个因子5的数：25 50 75 100，补计1次，每个数的因子5实际上被统计了2次
     * 3.3. n/125统计有3个因子5的数：125 250 375，补计1次，每个数的因子5实际上被统计了3次
     * 时间复杂度：O(log₅n)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int trailingZeroes(int n) {
            int count = 0;
            while (n > 0) {
                // 第一次：n/5，第二次：n/25，第三次：n/125...
                n /= 5;
                // 累加每层的计次
                count += n;
            }
            return count;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：计算阶乘后转字符串统计尾随零的个数
     * 1. 时间复杂度
     * 1.1. 实际：O(n × log(n!)) 或近似 O(n² log n)
     * 阶乘计算 factorial(n)：
     * 递归调用 n 次
     * 每次乘法运算涉及大数（随着 n 增大，结果位数急剧增长）
     * 第 i 次乘法时，数字约有 O(i log i) 位
     * 总时间：O(1 + 2log2 + 3log3 + ... + n*log(n)) ≈ O(n² log n)
     * 1.2. 转换为字符串 String.valueOf(f)：
     * 需要遍历所有位数
     * 位数约为 O(log(n!)) ≈ O(n log n)
     * 时间：O(n log n)
     * 1.3. 统计尾随零：
     * 最多遍历 O(n log n) 位
     * 时间：O(n log n)
     * 1.4. 总体时间复杂度：O(n² log n)
     * 2. 空间复杂度
     * O(n log n)
     * 阶乘结果存储：n! 的位数约为 O(log(n!)) ≈ O(n log n)
     * 字符串存储：同样 O(n log n)
     * 递归栈深度：O(n)
     * 总体空间复杂度：O(n log n)
     * 3. 整数溢出
     * int 最大值约 2×10⁹，只能计算到 12!
     * 即使改用 long，也只能计算到 20!
     * 4. 效率极低
     * 实际效率确实非常差，完全不适用于较大的 n。
     */
    class Solution1 {
        public int trailingZeroes(int n) {
            if (n == 0)
                return 0;
            long f = factorial(n);
            String s = String.valueOf(f);
            int count = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) != '0')
                    break;
                count++;
            }
            return count;
        }

        long factorial(int n) {
            if (n == 1)
                return 1;
            return n * factorial(n - 1);
        }
    }


    public static void main(String[] args) {
        Solution solution = new FactorialTrailingZeroes().new Solution();
        // put your test code here

    }
}