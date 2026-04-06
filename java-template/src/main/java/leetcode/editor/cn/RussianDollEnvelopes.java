package leetcode.editor.cn;

import java.util.Arrays;

public class RussianDollEnvelopes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：二分
         * 1. 先按w升序排序，确保w维度上可以嵌套
         * 2. 对于w相同的h，降序排序（w相同无法嵌套，则h也不应该递增）
         * 3. 对h求最长严格递增子序列，即为俄罗斯套娃信封的最大数量
         * 时间复杂度：O(nlogn)
         * 空间复杂度：O(n)
         */
        public int maxEnvelopes(int[][] envelopes) {
            // w升序，w相同，h降序
            Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
            int n = envelopes.length;
            // 把高度提为一维数组
            int[] h = new int[n];
            for (int i = 0; i < n; i++)
                h[i] = envelopes[i][1];
            // 牌堆堆顶数组
            int[] top = new int[n];
            // 牌堆个数
            int piles = 0;
            // poker是h中按序抽出来的牌
            for (int poker : h) {
                // 对牌堆堆顶数组进行二分查找,找到可插入的左边界
                int left = 0, right = piles;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (top[mid] < poker)
                        left = mid + 1;
                    else
                        right = mid;
                }
                // left == piles表示,poker比所有牌堆堆顶都大,需要另立牌堆
                if (left == piles)
                    piles++;
                // left是二分查找找到的位置,poker放到该牌堆的堆顶
                top[left] = poker;
            }
            // 牌堆的数量就是最长严格递增子序列长度
            return piles;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路：将二维转化为一维后使用dp
         * 1. 先按w升序排序，确保w维度上可以嵌套
         * 2. 对于w相同的h，降序排序（w相同无法嵌套，则h也不应该递增）
         * 3. 对h求最长严格递增子序列，即为俄罗斯套娃信封的最大数量
         * 时间复杂度：O(n^2)
         * 空间复杂度：O(n)
         * 【Time Limit Exceeded】
         */
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
            int n = envelopes.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < i; j++)
                    if (envelopes[j][1] < envelopes[i][1])
                        dp[i] = Math.max(dp[i], dp[j] + 1);
            int res = 0;
            for (int val : dp)
                res = Math.max(res, val);
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new RussianDollEnvelopes().new Solution();
        // put your test code here
        solution.maxEnvelopes(new int[][]{
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        });
    }
}