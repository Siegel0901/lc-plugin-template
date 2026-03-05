package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {

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
         * 思路二:DFS
         */
        List<Double> ans = new ArrayList<>();
        List<Integer> levelSize = new ArrayList<>();

        public List<Double> averageOfLevels(TreeNode root) {
            if (root == null)
                return ans;
            dfs(root, 1);
            for (int i = 0; i < ans.size(); i++)
                ans.set(i, ans.get(i) / levelSize.get(i));
            return ans;
        }

        void dfs(TreeNode root, int depth) {
            if (root == null)
                return;
            if (ans.size() == depth - 1) {
                ans.add(root.val * 1.0);
                levelSize.add(1);
            } else {
                ans.set(depth - 1, ans.get(depth - 1) + root.val);
                levelSize.set(depth - 1, levelSize.get(depth - 1) + 1);
            }
            dfs(root.left, depth + 1);
            dfs(root.right, depth + 1);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一:BFS
         */
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> ans = new ArrayList<>();
            if (root == null)
                return ans;
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                double sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    sum += poll.val;
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
                ans.add(sum / size);
            }
            return ans;
        }
    }


    public static void main(String[] args) {
        Solution solution = new AverageOfLevelsInBinaryTree().new Solution();
        // put your test code here

    }
}