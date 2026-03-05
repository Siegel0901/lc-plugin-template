package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {

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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Queue<TreeNode> queue = new ArrayDeque<>();
            if (root == null)
                return res;
            queue.offer(root);
            while (!queue.isEmpty()) {
                LinkedList<Integer> level = new LinkedList<>();
                int size = queue.size();
                // 根据层序遍历中的层数判断下一层是否需要逆序，偶数层需要逆序，因此结果中含有奇数层时，下一层需要逆序
                boolean reverse = (res.size() & 1) == 1;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (reverse)
                        level.addFirst(poll.val);   // 头插法得到逆序
                    else
                        level.add(poll.val);
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
                res.add(level);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();
        // put your test code here

    }
}