package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

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
         * 1. 构造根节点：先序遍历的第一个节点，后序遍历的最后一个节点
         * 2. 先序遍历的第二个节点可能为左子树根节点，也有可能为右子树根节点，这里统一为左子树根节点，方便处理
         * 2. 构造左子树：根节点为先序遍历第二个节点，可根据后序遍历获取根节点左子树的元素个数获得先序遍历和后序遍历左子树区间
         * 3. 构造右子树：根据左子树元素个数获得先序遍历和后序遍历右子树区间
         * 4. 利用递归构造根节点的左子树和右子树
         *
         * @param preorder  先序遍历
         * @param postorder 后序遍历
         * @return 二叉树
         */
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            for (int i = 0; i < postorder.length; i++)
                valToIndex.put(postorder[i], i);
            return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
        }

        TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
            if (preStart > preEnd)
                return null;
            // 如果只有一个值，则直接令其为根节点，主要是后续需要判断preorder[preStart + 1]
            if (preStart == preEnd)
                return new TreeNode(preorder[preStart]);
            // 根节点为先序遍历区间的第一个节点
            int rootVal = preorder[preStart];
            // 令根节点的左子树的根节点为先序遍历区间的第二个节点（也有可能是右子树的根节点，这里统一为左子树根节点）
            int leftRootVal = preorder[preStart + 1];
            // 左子树根节点在后序遍历中的索引
            int index = valToIndex.get(leftRootVal);
            // 左子树的元素个数
            int leftSize = index - postStart + 1;
            // 当前根节点
            TreeNode root = new TreeNode(rootVal);
            // 构造左子树：前序[preStart + 1, preStart + leftSize] 后序[postStart, index]
            root.left = build(preorder, preStart + 1, preStart + leftSize, postorder, postStart, index);
            // 构造右子树：前序[preStart + leftSize + 1, preEnd] 后序[index + 1, postEnd - 1]
            root.right = build(preorder, preStart + leftSize + 1, preEnd, postorder, index + 1, postEnd - 1);
            return root;
        }

        Map<Integer, Integer> valToIndex = new HashMap<>();

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
        // put your test code here

    }
}