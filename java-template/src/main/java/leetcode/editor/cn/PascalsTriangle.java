package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：数学公式
     * 1. 杨辉三角第n行第k个元素等于组合数C(n,k),即从n个元素中选k个的方案数
     * 2. 组合数定义：C(n, k) = n! / (k! * (n-k)!)
     * 3. 证明：
     * C(n, k) = n! / (k! * (n-k)!)
     * C(n, k+1) = n! / ((k+1)! * (n-k-1)!)
     *           = n! / ((k+1) * k! * (n-k-1)!)
     * 现在计算 C(n, k+1) / C(n, k)：
     * C(n, k+1) / C(n, k) = [n! / ((k+1) * k! * (n-k-1)!)] / [n! / (k! * (n-k)!)]
     *                     = [k! * (n-k)!] / [(k+1) * k! * (n-k-1)!]（约掉n!，(1/a)/(1/b)=b/a）
     *                     = (n-k)! / [(k+1) * (n-k-1)!]（约掉k!）
     *                     = (n-k) * (n-k-1)! / [(k+1) * (n-k-1)!]（约掉(n-k-1)!）
     *                     = (n-k) / (k+1)
     * 因此：
     * C(n, k+1) = C(n, k) * (n-k) / (k+1)  ✓
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> row = new ArrayList<>();
                int num = 1;
                for (int j = 0; j <= i; j++) {
                    // C(i,0)
                    row.add(num);
                    // C(i, j+1) = C(i, j) * (i-j) / (j+1)
                    num = num * (i - j) / (j + 1);
                }
                res.add(row);
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：模拟 - 逐行构建，首尾为1，中间元素为上一行相邻两数之和
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1) 除返回值外不需要额外空间
     * */
    class Solution1 {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> row = new ArrayList<>();
                // 第i+1行有i+1个元素
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i)
                        // 首尾元素为1
                        row.add(1);
                    else
                        // 非首尾元素为上一行相邻两个数相加
                        row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
                res.add(row);
            }
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
        // put your test code here
        System.out.println(solution.generate(5));
    }
}