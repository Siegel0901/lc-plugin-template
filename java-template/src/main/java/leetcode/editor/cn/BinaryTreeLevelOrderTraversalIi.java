package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.*;

public class BinaryTreeLevelOrderTraversalIi {

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
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            LinkedList<List<Integer>> ans = new LinkedList<>();
            Queue<TreeNode> queue = new ArrayDeque<>();
            if (root == null)
                return ans;
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> layer = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    layer.add(poll.val);
                    if (poll.left != null)
                        queue.offer(poll.left);
                    if (poll.right != null)
                        queue.offer(poll.right);
                }
                // 头插法得到逆序
                ans.addFirst(layer);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversalIi().new Solution();
        // put your test code here

    }
}