package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllPossibleFullBinaryTrees {

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
         * 思路：分解问题
         * 1. 真二叉树的左右子树都是真二叉树
         * 2. 根节点加上左右子树的节点之和为n
         * 3. 真二叉树的节点个数为奇数
         */
        public List<TreeNode> allPossibleFBT(int n) {
            // 偶数节点直接返回空列表
            if ((n & 1) == 0)
                return new ArrayList<>();
            // 必须先给memo声明足够的空间
            memo = new ArrayList<>(Collections.nCopies(n + 1, null));
            return build(n);
        }

        // 备忘录，用于记录build(1)~build(n)的结果
        List<List<TreeNode>> memo;

        // 定义：输入n，生成节点数为n的所有可能真二叉树
        List<TreeNode> build(int n) {
            List<TreeNode> res = new ArrayList<>();
            // 边界条件
            if (n == 1) {
                // 若n为1，则直接构建该节点
                res.add(new TreeNode(0));
                return res;
            }
            // 先从备忘录中查找
            if (memo.get(n) != null)
                return memo.get(n);
            /*
             * n - 1个节点用于构建左右子树
             * i从1开始，每次+2确保奇数
             * n - 1为偶数，i为奇数
             * j = n - 1 - i为奇数
             * */
            for (int i = 1; i < n; i += 2) {
                int j = n - 1 - i;
                // 构建节点数为i的所有可能真二叉树
                List<TreeNode> leftSubTrees = build(i);
                // 构建节点数为j的所有可能真二叉树
                List<TreeNode> rightSubTrees = build(j);
                /*
                 * 所有可能的左右子树构建完成后，需要与根节点拼接
                 * 遍历可能的左右子树，组合出所有情况
                 * */
                for (TreeNode left : leftSubTrees) {
                    for (TreeNode right : rightSubTrees) {
                        // 构建根节点
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }

            // 将build(n)存入备忘录
            memo.set(n, res);
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new AllPossibleFullBinaryTrees().new Solution();
        // put your test code here

    }
}