package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class CompleteBinaryTreeInserter {

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
    class CBTInserter {
        Deque<TreeNode> dq;
        TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;
            dq = bfs(root);
        }

        // 按层序遍历顺序找到完全二叉树中，可以插入孩子的节点
        Deque<TreeNode> bfs(TreeNode root) {
            // 遍历节点
            Deque<TreeNode> dq = new ArrayDeque<>();
            // 按层序遍历顺序记录节点
            Deque<TreeNode> res = new ArrayDeque<>();
            dq.offer(root);
            while (!dq.isEmpty()) {
                TreeNode cur = dq.poll();
                // 记录只有左孩子的节点
                if (cur.left != null && cur.right == null)
                    res.offer(cur);
                // 记录叶子节点
                if (cur.left == null && cur.right == null)
                    res.offer(cur);
                if (cur.left != null) dq.offer(cur.left);
                if (cur.right != null) dq.offer(cur.right);
            }
            return res;
        }

        public int insert(int val) {
            TreeNode node = new TreeNode(val);
            // 取出第一个可插入孩子的节点
            TreeNode cur = dq.peek();
            if (cur.left == null) {
                // 若为叶子节点,则插入为左孩子
                cur.left = node;
            } else if (cur.right == null) {
                // 若有左孩子,则插入为右孩子
                cur.right = node;
                // 第一个可插入节点的左右孩子都有了,不可再插入节点
                dq.poll();
            }
            // 新节点为叶子节点，也是可插入孩子的节点
            dq.offer(node);
            return cur.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }

    /**
     * Your CBTInserter object will be instantiated and called as such:
     * CBTInserter obj = new CBTInserter(root);
     * int param_1 = obj.insert(val);
     * TreeNode param_2 = obj.get_root();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        CBTInserter solution = new CompleteBinaryTreeInserter().new CBTInserter(TreeNode.createRoot(new Integer[]{1, 2}));
        // put your test code here
    }
}