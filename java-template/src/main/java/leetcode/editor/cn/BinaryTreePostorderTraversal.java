package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal {

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
        private final List<Integer> postorderTraversal = new ArrayList<>();

        private void traverse(TreeNode root) {
            if (root == null) return;
            traverse(root.left);
            traverse(root.right);
            postorderTraversal.add(root.val);
        }

        public List<Integer> postorderTraversal(TreeNode root) {
            postorderTraversal.clear();
            traverse(root);
            return postorderTraversal;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinaryTreePostorderTraversal().new Solution();
        // put your test code here

    }
}