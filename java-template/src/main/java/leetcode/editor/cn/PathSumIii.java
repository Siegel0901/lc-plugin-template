package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.HashMap;

public class PathSumIii {

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
    /*
     * 思路二：哈希表记录前缀和+DFS
     * 1. 从根节点到当前节点的路径上，维护一个“前缀和”
     * 1.1. 如果路径A（根->某祖先）的和为sum1，路径B（根->当前节点）的和为sum2，则路径C（某祖先->当前节点）的和为sum2-sum1
     * 1.2. 若sum2-target=sum1，则说明找到了一条和为target的路径
     * 2. 在遍历过程中，用哈希表记录路径和以及该路径和出现的次数
     * 3. 每次得到路径和sum2时，去哈希表中寻找sum1=sum2-target，累加路径和出现的次数
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution {
        // key为路径和，value为该路径和出现的次数，注意路径和需要用Long类型防止整型溢出
        HashMap<Long, Integer> preSumCnt = new HashMap<>();
        int target;
        long preSum = 0;
        int res = 0;

        public int pathSum(TreeNode root, int targetSum) {
            if (root == null)
                return 0;
            target = targetSum;
            // 路径和为0出现的次数为1
            preSumCnt.put(preSum, 1);
            dfs(root);
            return res;
        }

        void dfs(TreeNode root) {
            if (root == null)
                return;
            // 计算加上当前节点的路径和
            preSum += root.val;
            // 累加preSum-target路径和个数
            res += preSumCnt.getOrDefault(preSum - target, 0);
            // 当前路径和计数
            preSumCnt.put(preSum, preSumCnt.getOrDefault(preSum, 0) + 1);
            dfs(root.left);
            dfs(root.right);
            /*
            * 退出当前节点时,需要还原preSumCnt和preSum
            * 否则会污染路径和状态
            * */
            preSumCnt.put(preSum, preSumCnt.get(preSum) - 1);
            preSum -= root.val;
        }


    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：暴力求解
     * 1. dfs遍历所有节点
     * 2. 对于每个节点，dfs计算其到达其他节点的路径，统计等于target的路径数
     * 3. 注意sum要声明为long类型，防止int相加溢出
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        int path = 0;
        long sum = 0;
        int target;

        public int pathSum(TreeNode root, int targetSum) {
            target = targetSum;
            dfs(root);
            return path;
        }

        private void dfs(TreeNode root) {
            if (root == null)
                return;
            sum = 0;
            calWeight(root);
            dfs(root.left);
            dfs(root.right);
        }

        private void calWeight(TreeNode root) {
            if (root == null)
                return;
            sum += root.val;
            if (sum == target)
                path++;
            calWeight(root.left);
            calWeight(root.right);
            sum -= root.val;
        }
    }


    public static void main(String[] args) {
        Solution solution = new PathSumIii().new Solution();
        // put your test code here
        System.out.println(solution.pathSum(TreeNode.createRoot(new Integer[]{1000000000, 1000000000, null, 294967296, null, 1000000000, null, 1000000000, null, 1000000000}), 0));
    }
}