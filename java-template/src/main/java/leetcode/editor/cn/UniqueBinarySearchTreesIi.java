package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesIi {

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
        public List<TreeNode> generateTrees(int n) {
            // 初始化备忘录
            meno = new ArrayList[n + 1][n + 1];
            return build(1, n);
        }

        // 备忘录
        List<TreeNode>[][] meno;

        // 返回[start,end]组成的互不相同的不同BST
        List<TreeNode> build(int start, int end) {
            List<TreeNode> res = new ArrayList<>();

            // 违反BST性质,只有一种可能:null节点
            if (start > end) {
                res.add(null);
                return res;
            }

            // 查备忘录
            if (meno[start][end] != null)
                return meno[start][end];

            // [start, end]范围内的每个整数都有可能做根节点
            for (int i = start; i <= end; i++) {
                // 返回[start,i-1]组成的互不相同的不同BST作为左子树
                List<TreeNode> left = build(start, i - 1);
                // 返回[i+1,end]组成的互不相同的不同BST作为右子树
                List<TreeNode> right = build(i + 1, end);
                // 将所有左右子树的可能情况拼接到根节点上
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        res.add(root);
                    }
                }
            }

            // 存入备忘录
            meno[start][end] = res;

            return res;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTreesIi().new Solution();
        // put your test code here

    }
}