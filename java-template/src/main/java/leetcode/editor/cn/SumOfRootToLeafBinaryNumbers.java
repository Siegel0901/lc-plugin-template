package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class SumOfRootToLeafBinaryNumbers {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        /**
         * 思路：DFS
         * 1. 遍历到叶子节点时，将路径转换为十进制
         * 2. 将该十进制累加到结果
         * 3. 利用位运算可提高效率
         * 3.1. path = path << 1 | root.val;
         * 3.2. 表示path右移一位（补0）与root.val按位或，则path最后一位就是root.val原来的值
         * 3.3. 等价于path = path * 2 + root.val，只不过位运算更快
         */
        public int sumRootToLeaf(TreeNode root) {
            traverse(root);
            return sum;
        }

        int sum = 0;
        int path = 0;

        void traverse(TreeNode root) {
            if (root == null)
                return;
            path = path << 1 | root.val;
            if (root.left == null && root.right == null)
                sum += path;
            traverse(root.left);
            traverse(root.right);
            path = path >> 1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SumOfRootToLeafBinaryNumbers().new Solution();
        // put your test code here

    }
}