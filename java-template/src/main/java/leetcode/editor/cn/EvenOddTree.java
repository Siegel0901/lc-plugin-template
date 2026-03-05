package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class EvenOddTree {

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
        public boolean isEvenOddTree(TreeNode root) {
            dfs(root, 0);
            return flag;
        }

        List<Integer> levels = new ArrayList<>();
        boolean flag = true;

        void dfs(TreeNode root, int depth) {
            if (root == null)
                return;
            boolean even = (depth & 1) == 0;
            int val = root.val;
            boolean evenVal = (val & 1) == 0;
            if (levels.size() == depth) {
                if (even && evenVal || !even && !evenVal) {
                    flag = false;
                    return;
                }
                levels.add(val);
            } else {
                Integer prev = levels.get(depth);
                if ((even && (evenVal || prev >= val)) || (!even && (!evenVal || prev <= val))) {
                    flag = false;
                    return;
                }
                levels.set(depth, val);
            }
            dfs(root.left, depth + 1);
            dfs(root.right, depth + 1);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        public boolean isEvenOddTree(TreeNode root) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            // 初始为偶层
            boolean even = true;
            while (!queue.isEmpty()) {
                int size = queue.size();
                // prev与当前节点的值进行比较
                int prev = even ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    int val = poll.val;
                    boolean evenVal = (val & 1) == 0;
                    /*
                     * 偶层奇数严增为真,偶层偶数或偶层相等递减为假
                     * 奇层偶数严减为真,奇层奇数或奇层相等递增为假
                     * */
                    if ((even && (evenVal || prev >= val)) || (!even && (!evenVal || prev <= val)))
                        return false;
                    // 对比结束,更新上一个节点为当前节点
                    prev = val;
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
                // 转换奇偶层
                even = !even;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new EvenOddTree().new Solution();
        // put your test code here
        System.out.println(solution.isEvenOddTree(TreeNode.createRoot(new Integer[]{13, 34, 32, 23, 25, 27, 29, 44, 40, 36, 34, 30, 30, 28, 26, 3, 7, 9, 11, 15, 17, 21, 25, null, null, 27, 31, 35, null, 37, null, 30, null, 26, null, null, null, 24, null, 20, 16, 12, 10, null, null, 8, null, null, null, null, null, 6, null, null, null, null, null, 15, 19, null, null, null, null, 23, null, 27, 29, 33, 37, null, null, null, null, null, null, 48, null, null, null, 46, null, null, null, 42, 38, 34, 32, null, null, null, null, 19})));
    }
}