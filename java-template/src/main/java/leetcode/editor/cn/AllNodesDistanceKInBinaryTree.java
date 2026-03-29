package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.*;

public class AllNodesDistanceKInBinaryTree {

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

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            dfs(root, null);
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> dq = new ArrayDeque<>();
            dq.offer(target);
            visited.add(target.val);
            int step = 0;
            while (!dq.isEmpty()) {
                int sz = dq.size();
                if (step == k) {
                    for (int i = 0; i < sz; i++)
                        res.add(dq.poll().val);
                    return res;
                }
                for (int i = 0; i < sz; i++) {
                    TreeNode cur = dq.poll();
                    List<TreeNode> neighs = new ArrayList<>();
                    neighs.add(cur.left);
                    neighs.add(cur.right);
                    neighs.add(parents.get(cur.val));
                    for (TreeNode neigh : neighs) {
                        if (neigh == null || visited.contains(neigh.val))
                            continue;
                        dq.offer(neigh);
                        visited.add(neigh.val);
                    }
                }
                step++;
            }
            return res;
        }

        Map<Integer, TreeNode> parents = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        void dfs(TreeNode root, TreeNode parent) {
            if (root == null)
                return;

            parents.put(root.val, parent);

            dfs(root.left, root);
            dfs(root.right, root);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new AllNodesDistanceKInBinaryTree().new Solution();
        // put your test code here
    }
}