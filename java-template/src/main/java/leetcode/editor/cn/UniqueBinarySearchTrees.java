package leetcode.editor.cn;

public class UniqueBinarySearchTrees {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：分解问题
         * 1. 对于1到n的每个整数都有可能作为BST的根节点
         * 2. 取i为根节点，则[1, i-1]作为i的左子树，[i+1, n]作为i的右子树
         * 3. [1, i-1]和[i+1, n]为BST的可能种数为a和b，则[1,n]中以i为根节点的BST可能种数为a*b
         * 4. 利用备忘录消除重叠子问题
         */
        public int numTrees(int n) {
            // 初始化备忘录
            memo = new int[n + 1][n + 1];
            return count(1, n);
        }

        // 备忘录
        int[][] memo;

        int count(int start, int end) {
            if (start > end)
                return 1;
            // 查备忘录
            if (memo[start][end] != 0)
                return memo[start][end];
            int res = 0;
            // 遍历1~n，让每个数都作为根节点
            for (int i = start; i <= end; i++) {
                // 左子树的可能BST种数
                int left = count(start, i - 1);
                // 右子树的可能BST种数
                int right = count(i + 1, end);
                // 以i为根节点的BST种数
                res += left * right;
            }
            // 写备忘录
            memo[start][end] = res;
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTrees().new Solution();
        // put your test code here

    }
}