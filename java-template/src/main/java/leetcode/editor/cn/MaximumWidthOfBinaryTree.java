package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {

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
         * 思路三：DFS
         * 1. 对二叉树进行DFS
         * 2. 在DFS的同时，维护当前层数和每个节点的编号
         * 3. 若当前节点的编号为i，则左右孩子的编号为2*i，2*i+1
         * 4. 每层的宽度为尾节点编号 - 首节点编号 + 1
         * 5. 每次进入下一层时，记录该层第一个节点的编号
         * 6. 计算该层的其余节点编号与首节点编号的宽度，更新最大宽度
         */
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null)
                return 0;
            dfs(root, 1, 1);
            return maxWidth;
        }

        List<Integer> firstId = new ArrayList<>();
        int maxWidth = 1;

        void dfs(TreeNode root, int depth, int id) {
            if (root == null)
                return;
            if (firstId.size() == depth - 1)
                // firstId.size()推算当前的层数，记录每层的首节点id
                firstId.add(id);
            else
                // 其余节点的id则用于更新最大宽度
                maxWidth = Math.max(maxWidth, id - firstId.get(depth - 1) + 1);
            dfs(root.left, depth + 1, id * 2);
            dfs(root.right, depth + 1, id * 2 + 1);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution2 {
        class Pair {
            TreeNode node;
            int id;

            public Pair(TreeNode node, int id) {
                this.node = node;
                this.id = id;
            }
        }

        /**
         * 思路二：层序遍历
         * 1. 对二叉树进行层序遍历
         * 2. 在层序遍历的同时，维护每个节点的编号
         * 3. 若当前节点的编号为i，则左右孩子的编号为2*i，2*i+1
         * 4. 每行的宽度为尾节点编号 - 首节点编号 + 1
         */
        public int widthOfBinaryTree(TreeNode root) {
            int maxWidth = 1;
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(root, 1));
            while (!queue.isEmpty()) {
                int size = queue.size();
                int first = 0, last = 0;
                for (int i = 1; i <= size; i++) {
                    Pair poll = queue.poll();
                    // first记录第一个节点的编号
                    if (i == 1) first = poll.id;
                    // last记录最后一个节点的编号
                    if (i == size) last = poll.id;
                    if (poll.node.left != null)
                        queue.offer(new Pair(poll.node.left, poll.id * 2));
                    if (poll.node.right != null)
                        queue.offer(new Pair(poll.node.right, poll.id * 2 + 1));
                }
                maxWidth = Math.max(maxWidth, last - first + 1);
            }
            return maxWidth;
        }
    }

    class Solution1 {
        /**
         * 思路一：层序遍历
         * 1. 对二叉树进行层序遍历
         * 2. 不对节点的左右孩子进行null值的判断
         * 3. 若当前节点为null值，则其左右孩子都为null，加入队列
         * 4. 在每层的遍历过程中，first，last变量记录最后一个非null节点的索引，last - first + 1即为最大宽度
         * 5. 若first和last都为0，说明当前层全为null节点，退出循环
         * 【Time Limit Exceeded】
         */
        public int widthOfBinaryTree(TreeNode root) {
            int max = 1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                int first = 0, last = 0;
                for (int i = 1; i <= size; i++) {
                    TreeNode poll = queue.poll();
                    // null节点的两个null子节点入队
                    if (poll == null) {
                        queue.offer(null);
                        queue.offer(null);
                        continue;
                    }
                    // first记录第一个非null节点的索引
                    if (first == 0) first = i;
                    // last记录最后一个非null节点的索引
                    last = i;
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
                // 计算宽度
                int width = last - first + 1;
                max = Math.max(max, width);
                // 若first和last都为初值,则说明该层全为null节点,退出循环
                if (first == 0 && last == 0)
                    break;
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Solution solution = new MaximumWidthOfBinaryTree().new Solution();
        // put your test code here

    }
}