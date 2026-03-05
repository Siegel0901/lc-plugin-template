package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessOfABinaryTree {

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
         * 思路二：BFS
         * 1. 完全二叉树在出现了一个null节点之后，后续的节点应该都为null节点
         * 2. 否则不是完全二叉树
         */
        public boolean isCompleteTree(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // end表明是否已经遍历完所有的非null节点，之前的所有节点构成完全二叉树
            boolean end = false;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll == null)
                        // 遇到null节点
                        end = true;
                    else {  // 非null节点
                        if (end)
                            // 若之前遇到过null节点，则说明不是完全二叉树
                            return false;
                        queue.offer(poll.left);
                        queue.offer(poll.right);
                    }
                }
            }
            return true;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        class Pair {
            TreeNode node;
            int id;

            public Pair(TreeNode node, int id) {
                this.node = node;
                this.id = id;
            }
        }

        /**
         * 思路一：BFS
         * 1. BFS过程中为所有节点编号
         * 2. 左孩子为2*i，右孩子为2*i+1
         * 3. 同时记录当前遍历的是第几个节点
         * 4. 若编号与节点数不相等则不是完全二叉树
         */
        public boolean isCompleteTree(TreeNode root) {
            Queue<Pair> queue = new ArrayDeque<>();
            queue.offer(new Pair(root, 1));
            int count = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Pair poll = queue.poll();
                    count++;
                    if (count != poll.id)
                        return false;
                    if (poll.node.left != null) queue.offer(new Pair(poll.node.left, poll.id * 2));
                    if (poll.node.right != null) queue.offer(new Pair(poll.node.right, poll.id * 2 + 1));
                }
            }
            return true;
        }
    }


    public static void main(String[] args) {
        Solution solution = new CheckCompletenessOfABinaryTree().new Solution();
        // put your test code here

    }
}