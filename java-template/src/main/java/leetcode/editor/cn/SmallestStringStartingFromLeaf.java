package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class SmallestStringStartingFromLeaf {

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
         * 思路：DFS遍历
         * 1. 遍历到叶子节点时，构建路径，判断大小
         * 2. 如何判断两个路径的字典序大小？
         * 2.1. 看路径中的节点个数，节点个数小的路径字典序小
         * 2.2. 若路径中的节点个数相同，则从后向前遍历，相同位置元素小的字典序小
         * 3. 或者直接使用Java中的String对象的compareTo方法
         */
        public String smallestFromLeaf(TreeNode root) {
            traverse(root);
            return min;
        }

        String min = null;
        StringBuilder path = new StringBuilder();

        void traverse(TreeNode root) {
            if (root == null)
                return;
            path.append((char) ('a' + root.val));
            if (root.left == null && root.right == null) {
                String s = path.reverse().toString();
                if (min == null || s.compareTo(min) < 0)
                    min = s;
                path.reverse();
            }
            traverse(root.left);
            traverse(root.right);
            path.deleteCharAt(path.length() - 1);
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SmallestStringStartingFromLeaf().new Solution();
        // put your test code here
        String s = solution.smallestFromLeaf(TreeNode.createRoot(new Integer[]{0, 1, 2, 3, 4, 3, 4}));
        System.out.println(s);
    }
}