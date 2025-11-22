package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    class Solution {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            // original为工作结点,递归出口:original == null || original == target
            if (original == null || original == target)
                return cloned;
            // 在左子树中寻找结果
            TreeNode leftRes = getTargetCopy(original.left, cloned.left, target);
            if (leftRes != null) return leftRes;
            // 在右子树中寻找结果
            return getTargetCopy(original.right, cloned.right, target);
        }
    }
/*
    class Solution {
        private int originalIndex;
        private int clonedIndex;
        private TreeNode target;
        private boolean found;

        private void traverseOriginal(TreeNode root) {
            if (root == null || found) return;
            originalIndex++;
            if (root == target) {
                found = true;
                return;
            }
            traverseOriginal(root.left);
            traverseOriginal(root.right);
        }

        private void traverseCloned(TreeNode root) {
            if (root == null || found) return;
            clonedIndex++;
            if (clonedIndex == originalIndex) {
                found = true;
                target = root;
            }
            traverseCloned(root.left);
            traverseCloned(root.right);
        }

        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            this.target = target;
            traverseOriginal(original);
            found = false;
            traverseCloned(cloned);
            return this.target;
        }
    }
*/

    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree().new Solution();
        // put your test code here
        TreeNode original = new TreeNode(7);
        original.left = new TreeNode(4);
        original.right = new TreeNode(3);
        TreeNode cloned = new TreeNode(7);
        cloned.left = new TreeNode(4);
        cloned.right = new TreeNode(3);
        TreeNode target = original.right;
        solution.getTargetCopy(original, cloned, target);

    }
}