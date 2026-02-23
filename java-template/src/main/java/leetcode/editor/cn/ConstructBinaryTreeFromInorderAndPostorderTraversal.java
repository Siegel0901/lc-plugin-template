package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

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
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++)
                valToIndex.put(inorder[i], i);
            return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }

        TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
            if (inStart > inEnd)
                return null;
            int rootVal = postorder[postEnd];
            TreeNode root = new TreeNode(rootVal);
            Integer index = valToIndex.get(rootVal);
            // (inStart, index - 1)
            int leftSize = index - inStart;
            root.left = build(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
            root.right = build(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
            return root;
        }

        Map<Integer, Integer> valToIndex = new HashMap<>();
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        // put your test code here

    }
}