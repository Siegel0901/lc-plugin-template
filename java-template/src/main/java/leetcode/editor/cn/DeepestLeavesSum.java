package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DeepestLeavesSum {

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
        public int deepestLeavesSum(TreeNode root) {
            dfs(root, 0);
            return levelSum.get(levelSum.size() - 1);
        }

        List<Integer> levelSum = new ArrayList<>();

        void dfs(TreeNode root, int depth) {
            if (root == null)
                return;
            if (levelSum.size() == depth)
                levelSum.add(root.val);
            else
                levelSum.set(depth, levelSum.get(depth) + root.val);
            dfs(root.left, depth + 1);
            dfs(root.right, depth + 1);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        public int deepestLeavesSum(TreeNode root) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                int sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    sum += poll.val;
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
                if (queue.isEmpty())
                    return sum;
            }
            return 0;
        }
    }


    public static void main(String[] args) {
        Solution solution = new DeepestLeavesSum().new Solution();
        // put your test code here

    }
}