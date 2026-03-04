package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {

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
         * 思路一：层序遍历
         * 1. 利用层序遍历可以得到每一行的最后一个元素即为右视图
         * 2. 对每层遍历的顺序采用逆序（右->左），先遍历右孩子，可进一步提高效率
         */
//        public List<Integer> rightSideView(TreeNode root) {
//            List<Integer> res = new ArrayList<>();
//            if (root == null)
//                return res;
//            Queue<TreeNode> queue = new ArrayDeque<>();
//            queue.offer(root);
//            while (!queue.isEmpty()) {
//                res.add(queue.peek().val);
//                int size = queue.size();
//                for (int i = 0; i < size; i++) {
//                    TreeNode poll = queue.poll();
//                    if (poll.right != null)
//                        queue.offer(poll.right);
//                    if (poll.left != null)
//                        queue.offer(poll.left);
//                }
//            }
//            return res;
//        }

        /**
         * 思路二：DFS
         * 1. 右视图的节点个数一定与二叉树的深度相等
         * 2. 利用DFS计算二叉树的深度
         * 3. 采用先遍历右子树的方式
         * 4. 若深度与结果节点个数不等，则添加当前节点的值到结果中
         */
        public List<Integer> rightSideView(TreeNode root) {
            traverse(root);
            return res;
        }

        List<Integer> res = new ArrayList<>();
        int depth = 0;

        void traverse(TreeNode root) {
            if (root == null)
                return;
            depth++;
            if (depth > res.size())
                res.add(root.val);
            traverse(root.right);
            traverse(root.left);
            depth--;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinaryTreeRightSideView().new Solution();
        // put your test code here

    }
}