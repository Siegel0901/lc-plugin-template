package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {

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
        private final List<Integer> inorderTraversal = new ArrayList<>();

        private void traverse(TreeNode root) {
            if (root == null) return;
            traverse(root.left);
            inorderTraversal.add(root.val);
            traverse(root.right);
        }

        public List<Integer> inorderTraversal(TreeNode root) {
            inorderTraversal.clear();
            traverse(root);
            return inorderTraversal;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
        // put your test code here

    }
}