package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class CountCompleteTreeNodes {

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
         * 思路一：遍历
         * 时间复杂度：O(n)
         * 空间复杂度：O(logn)
         */
//        public int countNodes(TreeNode root) {
//            if (root == null)
//                return 0;
//            int leftCount = countNodes(root.left);
//            int rightCount = countNodes(root.right);
//            return leftCount + rightCount + 1;
//        }

        /**
         * 思路二：利用完全二叉树的性质
         * 完全二叉树中的左右子树必有一颗为满二叉树，另一颗为完全二叉树
         * 判断是否为满二叉树的时间复杂度：O(logn)
         * 完全二叉树遍历过程中每次都要判断当前子树是否为满二叉树，一共需判断O(logn)次
         * 时间复杂度：O(logn * logn)
         */
        public int countNodes(TreeNode root) {
            TreeNode l = root, r = root;
            int hl = 0, hr = 0;
            while (l != null) {
                l = l.left;
                hl++;
            }
            while (r != null) {
                r = r.right;
                hr++;
            }
            if (hl == hr)
                return (int) Math.pow(2, hl) - 1;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new CountCompleteTreeNodes().new Solution();
        // put your test code here

    }
}