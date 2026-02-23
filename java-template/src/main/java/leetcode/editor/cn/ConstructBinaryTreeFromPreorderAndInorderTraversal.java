package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

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
         * 思路：根据前序遍历 + 中序遍历特点还原二叉树
         * 1. 前序遍历节点为根节点
         * 2. 确定其在中序遍历的位置pos
         * 3. 中序遍历pos之前的为左子树
         * 4. 中序遍历pos之后的为右子树
         * 5. 由于题目保证无重复元素，则可用HashMap替代for循环寻找preorder[Start]在inorder中的索引
         *
         * @param preorder 前序遍历结果
         * @param inorder  中序遍历结果
         * @return 根节点
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0; i < inorder.length; i++)
                valToIndex.put(inorder[i], i);
            return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        Map<Integer, Integer> valToIndex = new HashMap<>();

        TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd)
                return null;
            int rootVal = preorder[preStart];
            TreeNode root = new TreeNode(rootVal);
            int index = valToIndex.get(rootVal);
            // (inStart, index - 1)
            int leftSize = index - inStart;
            root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
            root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        // put your test code here

    }
}