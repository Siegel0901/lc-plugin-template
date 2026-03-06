package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class ConvertBstToGreaterTree {

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
         * 思路二：逆序中序遍历
         * 1. 先遍历右子树，再遍历左子树
         * 2. 每个节点的累加和等于已经遍历过的节点值总和（包括自己）
         */
        public TreeNode convertBST(TreeNode root) {
            dfs(root);
            return root;
        }

        int sum = 0;

        void dfs(TreeNode root) {
            if (root == null)
                return;
            // 先遍历右子树
            dfs(root.right);
            /*
             * 中序区域
             * 将当前节点的值改为累加和（包括自身）
             * */
            sum += root.val;
            root.val = sum;
            // 再遍历左子树
            dfs(root.left);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一：先计算总和sum，每个节点的值为总和sum减去中序遍历已经遍历过的节点的和beforeSum
         */
        public TreeNode convertBST(TreeNode root) {
            totalSum(root);
            dfs(root);
            return root;
        }

        int sum = 0;
        int beforeSum = 0;

        void totalSum(TreeNode root) {
            if (root == null)
                return;
            totalSum(root.left);
            sum += root.val;
            totalSum(root.right);
        }

        void dfs(TreeNode root) {
            if (root == null)
                return;
            dfs(root.left);
            int val = root.val;
            root.val = sum - beforeSum;
            beforeSum += val;
            dfs(root.right);
        }
    }


    public static void main(String[] args) {
        Solution solution = new ConvertBstToGreaterTree().new Solution();
        // put your test code here

    }
}