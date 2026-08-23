package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MergeTwoBinaryTrees {

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
    /*
     * 思路二：BFS遍历
     * 1. 遍历两棵树，在遍历过程中合并
     * 时间复杂度：O(min(m,n))
     * 空间复杂度：O(min(m,n))
     * */
    class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null)
                return root2;
            if (root2 == null)
                return root1;
            Deque<TreeNode[]> q = new ArrayDeque<>();
            q.offer(new TreeNode[]{root1, root2});
            while (!q.isEmpty()) {
                TreeNode[] poll = q.poll();
                TreeNode node1 = poll[0];
                TreeNode node2 = poll[1];
                if (node1 == null || node2 == null)
                    continue;
                // node1和node2都不为空，则值相加
                node1.val += node2.val;
                /*
                * node1的子树要是为空，直接把对应的node2的子树拿过来，遍历分支结束
                * node1的子树要是不为空，则把node1和node2的对应子树加入队列，继续遍历node1
                * */
                if (node1.left == null)
                    // 这里node2不需要置空的原因是后面不再访问了
                    node1.left = node2.left;
                else
                    q.offer(new TreeNode[]{node1.left, node2.left});
                if (node1.right == null)
                    node1.right = node2.right;
                else
                    q.offer(new TreeNode[]{node1.right, node2.right});
            }
            return root1;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路二：DFS遍历
     * 1. 遍历两棵树，在遍历过程中合并
     * 时间复杂度：O(min(m,n))
     * 空间复杂度：O(min(m,n))
     * */
    class Solution2 {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null)
                return root2;
            dfs(root1, root2);
            return root1;
        }

        private void dfs(TreeNode root1, TreeNode root2) {
            if (root1 == null || root2 == null)
                return;
            // 两棵树都有的节点，叠加节点值
            root1.val += root2.val;
            // 如果 root1 没有子树而 root2 有，那么就把 root2 的子树接到 root1 上
            // 注意接完之后把 root2 的子树置为 null，因为后面还要递归访问root2的子树，免得错误计算节点累加值
            if (root1.left == null && root2.left != null) {
                root1.left = root2.left;
                root2.left = null;
            }
            if (root1.right == null && root2.right != null) {
                root1.right = root2.right;
                root2.right = null;
            }
            // 递归遍历左右子节点，root2 的节点也跟着同步移动
            dfs(root1.left, root2.left);
            dfs(root1.right, root2.right);
        }
    }

    /*
     * 思路一：分解问题
     * 1. 合并树 = 合并左子树 + 合并右子树
     * 时间复杂度：O(min(m,n))
     * 空间复杂度：O(min(m,n))
     * */
    class Solution1 {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) return root2;
            if (root2 == null) return root1;
            root1.val += root2.val;
            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);
            return root1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new MergeTwoBinaryTrees().new Solution();
        // put your test code here
        solution.mergeTrees(TreeNode.createRoot(
                new Integer[]{1, 2, null, 3}
        ), TreeNode.createRoot(
                new Integer[]{1, null, 2, null, 3}
        ));
    }
}