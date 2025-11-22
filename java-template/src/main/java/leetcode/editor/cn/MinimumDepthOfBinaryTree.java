package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfBinaryTree {

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
/*    class Solution {
        private int minDepth = Integer.MAX_VALUE;

        private void traverse(TreeNode root, int depth) {
            if (root == null) return;
            if (root.left == null && root.right == null) {
                minDepth = Math.min(minDepth, depth);
            }
            traverse(root.left, depth + 1);
            traverse(root.right, depth + 1);
        }

        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            traverse(root, 1);
            return minDepth;
        }
    }*/
    class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    // 第一个叶子结点的深度就是最小深度
                    if (cur.left == null && cur.right == null)
                        return depth;
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
                depth++;
            }
            return depth;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();
        // put your test code here

    }
}