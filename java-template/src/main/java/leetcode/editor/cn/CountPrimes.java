package leetcode.editor.cn;

public class CountPrimes {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：埃拉托斯特尼筛法
     * 时间复杂度：O(nloglogn)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int countPrimes(int n) {
            if (n <= 2) return 0;
            // 初始为1,因为2是素数
            int count = 1;
            // 默认都是素数
            boolean[] notPrimes = new boolean[n];
            // 获取平方根,即上限
            int upper = (int) Math.sqrt(n);
            // 只需要遍历[3,n)中的奇数
            for (int i = 3; i < n; i += 2) {
                // i不是素数
                if (notPrimes[i])
                    continue;
                // i是素数
                count++;
                /*
                * 如果n有因子d > √n，那么必然有另一个因子n/d < √n
                * 假设 n = 100，√100 = 10
                * 如果 100 有因子 25（> 10），那么必有因子 4（= 100/25 < 10）
                * 所以在处理 i=4 时，已经把 100 标记为非素数了
                * 故i > √n时需要跳过：
                * 1. i仍是素数
                * 2. 但是不需要用i去筛其他数
                * */
                if (i > upper)
                    continue;
                // i的倍数都不是素数
                for (int j = i * i; j < n; j = j + 2 * i)
                    /*
                     * 1. j = i*i
                     * 从i*i开始，而不是从2*i开始，[2,i-1]*i之前遍历过已经确定不是素数了
                     * 2. j = j+2*i
                     * 跳过偶数倍数，只筛奇数，i是奇数，i*i也是奇数，奇数+2*奇数=奇数
                     * 若j+=i，则存在i*i+i = i*(i+1)，i为奇数，i+1为偶数，已经被筛过了
                     * */
                    notPrimes[j] = true;
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：遍历
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     */
    class Solution1 {
        public int countPrimes(int n) {
            int count = 0;
            if (n > 2)
                count++;
            for (int i = 3; i < n; i++) {
                if ((i & 1) == 0)
                    continue;
                count++;
                for (int j = 3; j < i; j += 2) {
                    if (i % j == 0) {
                        count--;
                        break;
                    }
                }
            }
            return count;
        }
    }


    public static void main(String[] args) {
        Solution solution = new CountPrimes().new Solution();
        // put your test code here

    }
}