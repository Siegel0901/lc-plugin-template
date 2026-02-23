package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class MaximumBinaryTree {

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
         * 思路：根据题意完成递归函数编写
         * 1. 构造根节点：区间最大值
         * 2. 构造左子树：最大值之前区间的最大值
         * 2. 构造右子树：最大值之后区间的最大值
         *
         * @param nums 数组
         * @return 最大二叉树
         */
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return construct(nums, 0, nums.length - 1);
        }

        TreeNode construct(int[] nums, int begin, int end) {
            if (begin > end)
                return null;
            int max = findMaxIndex(nums, begin, end);
            TreeNode root = new TreeNode(nums[max]);
            root.left = construct(nums, begin, max - 1);
            root.right = construct(nums, max + 1, end);
            return root;
        }

        int findMaxIndex(int[] nums, int begin, int end) {
            int max = begin;
            for (int i = begin; i <= end; i++)
                if (nums[i] > nums[max])
                    max = i;
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MaximumBinaryTree().new Solution();
        // put your test code here

    }
}