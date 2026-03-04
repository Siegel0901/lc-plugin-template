package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreePaths {

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
         * 思路：遍历
         * 1. 维护一个节点List，存入每个节点的值
         * 2. 当遍历到叶子节点时，将节点List转为String存入路径List
         * 3. 采用前序遍历，当前节点的左右子树都遍历完后，从sb中删除当前节点的值
         */
        public List<String> binaryTreePaths(TreeNode root) {
            traverse(root);
            return res;
        }

        List<String> res = new ArrayList<>();
        List<Integer> nodes = new ArrayList<>();

        String SEP = "->";

        void traverse(TreeNode root) {
            if (root == null)
                return;
            nodes.add(root.val);
            if (root.left == null && root.right == null) {
                String path = nodes.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(SEP));
                res.add(path);
            }
            traverse(root.left);
            traverse(root.right);
            nodes.remove(nodes.size() - 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinaryTreePaths().new Solution();
        // put your test code here

    }
}