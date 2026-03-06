package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class DeleteNodeInABst {

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
         * 思路：利用BST性质
         * 删除节点会出现的情况：
         * 1. 当前节点为叶子节点：直接删除
         * 2. 当前节点只有一个孩子节点：当前节点替换为孩子节点
         * 3. 当前节点有两个孩子节点：找到当前节点的中序遍历前驱（左子树最大值）或后继（右子树最小值）替换，转为情况1或情况2
         */
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null)
                return null;
            if (root.val == key) {
                // 情况1和情况2
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;
                // 情况3
//                // 获取右子树最小节点
//                TreeNode minNode = getMin(root.right);
//                // 在右子树中删除最小节点
//                root.right = deleteNode(root.right, minNode.val);
//                // 将当前节点设为最小节点
//                minNode.left = root.left;
//                minNode.right = root.right;
//                root = minNode;
                // 获取左子树最大节点
                TreeNode maxNode = getMax(root.left);
                // 在左子树中删除最大节点
                root.left = deleteNode(root.left, maxNode.val);
                // 将当前节点设为最大节点
                maxNode.left = root.left;
                maxNode.right = root.right;
                root = maxNode;
            }
            if (key < root.val)
                root.left = deleteNode(root.left, key);
            if (root.val < key)
                root.right = deleteNode(root.right, key);
            return root;
        }

        /**
         * BST中最左边的节点就是值最小的节点
         */
        TreeNode getMin(TreeNode node) {
            while (node.left != null)
                node = node.left;
            return node;
        }
        /**
         * BST中最右边的节点就是值最大的节点
         */
        TreeNode getMax(TreeNode node) {
            while (node.right != null)
                node = node.right;
            return node;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new DeleteNodeInABst().new Solution();
        // put your test code here

    }
}