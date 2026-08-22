package leetcode.editor.cn;

public class CountingBits {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
    * 思路二：DP
    * 1. 列出数据，找规律
        先写出 0~8 的二进制和 1 的个数：
        i    二进制    1的个数
        0    0000      0
        1    0001      1
        2    0010      1
        3    0011      2
        4    0100      1
        5    0101      2
        6    0110      2
        7    0111      3
        8    1000      1
    * 2. 观察相邻数字之间的关系
        看偶数和它除以 2 的关系：
        2 (10) → 1个     2/2 = 1 (1) → 1个     一样！
        4 (100) → 1个    4/2 = 2 (10) → 1个    一样！
        6 (110) → 2个    6/2 = 3 (11) → 2个    一样！
        规律：偶数 `i` 右移一位（`i >> 1`）只是去掉末尾的 `0`，1 的个数不变：dp[i] = dp[i >> 1]
        再看奇数和它前一个偶数的关系：
        3 (11) → 2个     3-1 = 2 (10) → 1个     多1个！
        5 (101) → 2个    5-1 = 4 (100) → 1个    多1个！
        7 (111) → 3个    7-1 = 6 (110) → 2个    多1个！
        规律：奇数 `i` 比 `i-1` 多一个末尾的 `1`：dp[i] = dp[i - 1] + 1
        也可以统一成一个写法（利用 `i & (i - 1)` 去掉最低位的 1）：
        dp[i] = dp[i & (i - 1)] + 1;
        原理：i - 1 的效果是把最低位的 1 变成 0，它右边所有的 0 变成 1。做 & 运算后，最低位的 1 及其右边的位全部归零，更高位不变。
                i     = ...1 0...0
                i - 1 = ...0 1...1
                       ──────────
                i&(i-1)= ...0 0...0
                          ↑
                     最低位的1被去掉了
        为什么 dp[i] = dp[i & (i-1)] + 1？
        i & (i-1) 比 i 恰好少了 1 个 1（最低位那个），所以：
        i 的 1 的个数 = (i & (i-1)) 的 1 的个数 + 1
        而且i & (i-1) < i，满足DP的递推顺序
    * 3. 为什么这就是 DP？
    * 3.1. 有重叠子问题：算 `dp[6]` 需要 `dp[3]`，算 `dp[7]` 也需要 `dp[6]`（进而需要 `dp[3]`）
    * 3.2. 有最优子结构：大问题的解可以由小问题的解推出
    * 3.3. 从小到大递推：`dp[0]` 已知，依次算 `dp[1], dp[2], ...`
    * 4. 定义数组：dp[i] 表示数字 i 的 1 的个数
    * 5. 状态转移方程：dp[i] = dp[i & (i - 1)] + 1;
    * 6. 复杂度分析：
    *    时间复杂度：O(n)。需要遍历 0 到 n 的每个数字，每次状态转移只需要 O(1) 的时间，总时间复杂度为 O(n)。
    *    空间复杂度：O(1)。如果不考虑作为结果返回的数组 dp，仅使用了常数个额外变量，额外空间复杂度为 O(1)。
    * */
    class Solution {
        public int[] countBits(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++)
                dp[i] = dp[i & (i - 1)] + 1;
            return dp;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：除2取余
     * 遍历 0 到 n，对每个数不断除以 2 并取余，统计 1 的个数。
     *
     * 时间复杂度：O(n log n)，外层循环 n 次，内层 while 循环执行 log(i) 次。
     * 空间复杂度：O(1)，不考虑返回的结果数组，仅使用了常数个额外变量。
     * */
    class Solution1 {
        public int[] countBits(int n) {
            int[] res = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                int num = i;
                while (num > 0) {
                    res[i] += num % 2;
                    num = num / 2;
                }
            }
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new CountingBits().new Solution();
        // put your test code here

    }
}