package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class ValidateBinarySearchTree {

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
         * 思路二：分解问题思维解题
         * 1. BST根节点的左右子树都是BST
         * 2. 根节点要大于左子树的所有节点值，小于右子树的所有节点值
         * 3. 取左子树的最大值节点为min，右子树的最小值节点为max，根节点满足：min.val < root.val < max.val
         * 3. 根节点范围：(-∞, +∞)
         * 4. 左孩子范围： (父节点的 min, 父节点)
         * 5. 右孩子范围： (父节点, 父节点的 max)
         * 6. min.val < root.left.val < root.val < root.right.val < max.val
         */
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        // min.val < root.left.val < root.val < root.right.val < max.val
        boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            if (root == null)
                return true;
            if (min != null && min.val >= root.val) return false;
            if (max != null && max.val <= root.val) return false;
            return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        /**
         * 思路一：遍历思维解题
         */
        public boolean isValidBST(TreeNode root) {
            dfs(root);
            return flag;
        }

        boolean flag = true;
        Integer prev = null;

        void dfs(TreeNode root) {
            if (root == null)
                return;
            dfs(root.left);
            if (prev != null && root.val <= prev)
                flag = false;
            prev = root.val;
            dfs(root.right);
        }
    }


    public static void main(String[] args) {
        Solution solution = new ValidateBinarySearchTree().new Solution();
        // put your test code here
        System.out.println(solution.isValidBST(TreeNode.createRoot(new Integer[]{0})));
    }
}