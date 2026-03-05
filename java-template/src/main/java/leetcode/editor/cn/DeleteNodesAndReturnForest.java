package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {

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
         * 思路二：将删除节点的左右孩子作为子树添加到森林
         */
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            if (root == null)
                return new ArrayList<>();
            for (int i : to_delete)
                delSet.add(i);
            // 判断根节点是否需要删除
            if (dfs(root) != null)
                res.add(root);
            return res;
        }

        Set<Integer> delSet = new HashSet<>();
        List<TreeNode> res = new ArrayList<>();

        // 定义：输入一颗二叉树，删除delSet中的节点，返回删除完成后的根节点
        TreeNode dfs(TreeNode root) {
            if (root == null)
                return null;
            // 遍历左子树进行删除操作
            root.left = dfs(root.left);
            // 遍历右子树进行删除操作
            root.right = dfs(root.right);
            // 判断当前节点是否需要删除,无需删除则返回根节点
            if (!delSet.contains(root.val)) return root;
            // 需要删除,则将左右子树加入森林
            if (root.left != null) res.add(root.left);
            if (root.right != null) res.add(root.right);
            // 删除当前节点,返回null
            return null;
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一：根据是否有父节点添加子树到森林
         */
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            if (root == null)
                return new ArrayList<>();
            // delSet = Arrays.stream(to_delete).boxed().collect(Collectors.toCollection(HashSet::new));
            for (int i : to_delete)
                delSet.add(i);
            doDelete(root, false);
            return res;
        }

        Set<Integer> delSet = new HashSet<>();
        List<TreeNode> res = new ArrayList<>();

        // 定义：输入一颗二叉树，删除delSet中的节点，返回删除完成后的根节点
        TreeNode doDelete(TreeNode root, boolean hasParent) {
            if (root == null)
                return null;
            boolean deleted = delSet.contains(root.val);
            // 如果不需要删除也没有父节点,则为一个新的根节点
            if (!deleted && !hasParent)
                res.add(root);
            /*
             * 对左右子树进行节点删除
             * !deleted含义：
             * 若当前节点需要删除，则左右子树没有父节点
             * 若当前节点不需要删除，则左右子树有父节点
             * */
            root.left = doDelete(root.left, !deleted);
            root.right = doDelete(root.right, !deleted);
            // 删除操作
            return deleted ? null : root;
        }

    }

    public static void main(String[] args) {
        Solution solution = new DeleteNodesAndReturnForest().new Solution();
        // put your test code here

    }
}