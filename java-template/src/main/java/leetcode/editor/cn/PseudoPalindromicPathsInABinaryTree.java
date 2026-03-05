package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

public class PseudoPalindromicPathsInABinaryTree {

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
         * 思路：DFS
         * 1. 遍历到叶子节点时，获取当前路径的所有结点的全排列
         * 2. 若全排列中有回文序列，则计数+1
         * 3. 如何判断有回文全排列？
         * 3.1. 统计路径中节点值的个数
         * 3.2. 只允许出现一个个数为奇数的节点
         * 4. 如何实现？
         * 4.1. 在遍历到叶子节点的时候统计个数【Time Limit Exceeded】
         * 4.2. 遍历过程中统计奇数个数
         * 4.3. 可以使用位运算提高效率
         */
        public int pseudoPalindromicPaths(TreeNode root) {
            traverse(root);
            return count;
        }

        //        int count = 0;
//        Map<Integer, Integer> path = new HashMap<>();
//        int oddNum = 0;
//
//        void traverse(TreeNode root) {
//            if (root == null)
//                return;
//            int val = root.val;
//            path.put(val, path.getOrDefault(val, 0) + 1);
//            oddNum += (path.get(val) & 1) == 1 ? 1 : -1;
//            if (root.left == null && root.right == null)
//                if (oddNum < 2)
//                    count++;
//            traverse(root.left);
//            traverse(root.right);
//            path.put(val, path.get(val) - 1);
//            oddNum += (path.get(val) & 1) == 1 ? 1 : -1;
//        }
        /*
         * 使用位运算提高效率
         * n的二进制表示0~9这10个数中，每个数的奇偶状态，初始都为0
         * 1. n ^= (1 << k)切换第k位的奇偶状态
         * 2. n & (n - 1)去除二进制的最后一个1
         * 3. (n & (n - 1)) == 0判断n的位数中是否最多只有一个奇数
         * 4. a ^ b ^ b = a回溯当前节点对应位数的奇偶状态
         * */
        int count = 0;
        int path = 0;

        void traverse(TreeNode root) {
            if (root == null)
                return;
            path = path ^ (1 << root.val);
            if (root.left == null && root.right == null)
                if ((path & (path - 1)) == 0)
                    count++;
            traverse(root.left);
            traverse(root.right);
            path = path ^ (1 << root.val);
        }

        /**
         * 判断是否有回文全排列
         * 【Time Limit Exceeded】
         */
//        boolean isPseudoPalindromicPath(List<Integer> path) {
//            Map<Integer, Integer> map = new HashMap<>();
//            for (Integer val : path) {
//                if (map.containsKey(val))
//                    map.put(val, map.get(val) + 1);
//                else
//                    map.put(val, 1);
//            }
//            int oddNum = 0;
//            for (Integer value : map.values())
//                if ((value & 1) == 1)
//                    if (++oddNum == 2)
//                        return false;
//            return true;
//        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PseudoPalindromicPathsInABinaryTree().new Solution();
        // put your test code here

    }
}