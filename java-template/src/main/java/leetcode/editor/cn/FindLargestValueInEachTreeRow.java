package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {

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
        public List<Integer> largestValues(TreeNode root) {
            if (root == null)
                return ans;
            dfs(root, 1);
            return ans;
        }

        List<Integer> ans = new ArrayList<>();

        void dfs(TreeNode root, int depth) {
            if (root == null)
                return;
            if (ans.size() == depth - 1)
                // 若为该层第一个节点，则加入ans作为最大值
                ans.add(root.val);
            else
                // 该层其余节点用于更新最大值
                ans.set(depth - 1, Math.max(ans.get(depth - 1), root.val));
            dfs(root.left, depth + 1);
            dfs(root.right, depth + 1);
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一:BFS
         */
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            if (root == null)
                return ans;
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int max = queue.peek().val;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    max = Math.max(max, poll.val);
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
                ans.add(max);
            }
            return ans;
        }
    }


    public static void main(String[] args) {
        Solution solution = new FindLargestValueInEachTreeRow().new Solution();
        // put your test code here

    }
}