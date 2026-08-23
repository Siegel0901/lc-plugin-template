package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.*;

public class SymmetricTree {

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
     * 思路五：双指针同步遍历
     * 1. 同时维护两个遍历指针
     * 1.1. 一个按"左-根-右"遍历左子树
     * 1.2. 另一个按"右-根-左"遍历右子树（镜像顺序）
     * 1.3. 每一步比较两个指针指向的节点值是否相同
     * */
    class Solution {
        /*
         * 迭代实现：两个栈
         * 时间复杂度：O(n)
         * 空间复杂度：O(h)
         * */
        public boolean isSymmetric(TreeNode root) {
            Deque<TreeNode> stk1 = new LinkedList<>();
            Deque<TreeNode> stk2 = new LinkedList<>();
            stk1.push(root.left);
            stk2.push(root.right);
            while (!stk1.isEmpty() && !stk2.isEmpty()) {
                TreeNode cur1 = stk1.pop();
                TreeNode cur2 = stk2.pop();
                if (cur1 == null && cur2 == null)
                    continue;
                if (cur1 == null || cur2 == null)
                    return false;
                if (cur1.val != cur2.val)
                    return false;
                // stk1访问顺序：根左右
                stk1.push(cur1.right);
                stk1.push(cur1.left);
                // stk2访问顺序：根右左
                stk2.push(cur2.left);
                stk2.push(cur2.right);
            }
            return true;
        }

        /**
         * 前序遍历二叉树的迭代实现
         */
        List<Integer> preorder(TreeNode root) {
            List<Integer> preorder = new ArrayList<>();
            if (root == null)
                return preorder;
            Deque<TreeNode> stk = new ArrayDeque<>();
            stk.push(root);
            while (!stk.isEmpty()) {
                TreeNode cur = stk.pop();
                preorder.add(cur.val);
                // 栈是后进先出，先出先访问，left在right后面，先访问left，满足前序的根左右顺序
                if (cur.right != null) stk.push(cur.right);
                if (cur.left != null) stk.push(cur.left);
            }
            return preorder;
        }
        /*
         * 递归实现
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         * */
//        public boolean isSymmetric(TreeNode root) {
//            List<Integer> order = new ArrayList<>();
//            List<Integer> reverseOrder = new ArrayList<>();
//            traverse(root, order, false);
//            traverse(root, reverseOrder, true);
//            for (int i = 0; i < order.size(); i++)
//                if (!order.get(i).equals(reverseOrder.get(i)))
//                    return false;
//            return true;
//        }

        void traverse(TreeNode root, List<Integer> order, boolean reverse) {
            if (root == null) {
                order.add(Integer.MIN_VALUE);
                return;
            }
            order.add(root.val);
            if (reverse) {
                traverse(root.right, order, true);
                traverse(root.left, order, true);
            } else {
                traverse(root.left, order, false);
                traverse(root.right, order, false);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路四：翻转对比法
     * 1. 复制原树的右子树
     * 2. 将右子树完全翻转（左右互换）
     * 3. 对比左子树和翻转后的右子树是否完全相同
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution4 {
        boolean isSame = true;

        public boolean isSymmetric(TreeNode root) {
            reverse(root.right);
            traverse(root.left, root.right);
            return isSame;
        }

        // 对比两个二叉树是否相同
        void traverse(TreeNode tree1, TreeNode tree2) {
            if (!isSame)
                return;
            if (tree1 == null && tree2 == null)
                return;
            if (tree1 == null || tree2 == null) {
                isSame = false;
                return;
            }
            if (tree1.val != tree2.val) {
                isSame = false;
                return;
            }
            traverse(tree1.left, tree2.left);
            traverse(tree1.right, tree2.right);
        }

        // 翻转二叉树
        void reverse(TreeNode root) {
            if (root == null)
                return;
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            reverse(root.left);
            reverse(root.right);
        }
    }

    /*
     * 思路三：层序遍历（BFS）+ 对称性检查
     * 1. 按层遍历二叉树，每层收集节点值（空节点需特殊标记）
     * 2. 判断每一层的序列是否回文
     * 3. 所有层都对称才返回 true
     * 4. 关键点：
     * 4.1. 必须记录空节点位置，否则无法区分结构差异
     * 4.2. 例如：[1,2,2,null,3,null,3] 这层需要判断 [null,3,null,3] 是否对称
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution3 {
        public boolean isSymmetric(TreeNode root) {
            Deque<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int sz = q.size();
                List<Integer> layer = new ArrayList<>();
                for (int i = 0; i < sz; i++) {
                    TreeNode poll = q.poll();
                    if (poll != null) {
                        layer.add(poll.val);
                        q.offer(poll.left);
                        q.offer(poll.right);
                    } else
                        layer.add(Integer.MIN_VALUE);
                }
                if (!isSymmetric(layer))
                    return false;
            }
            return true;
        }

        boolean isSymmetric(List<Integer> layer) {
            int left = 0, right = layer.size() - 1;
            while (left <= right)
                if (!layer.get(left++).equals(layer.get(right--)))
                    return false;
            return true;
        }
    }

    /*
     * 思路二：迭代法（队列/栈）
     * 1. 初始化：使用队列（或栈），初始时将 root.left 和 root.right 成对入队
     * 2. 循环处理：每次从队列中取出两个节点进行比较
     * 3. 比较逻辑：
     * 3.1. 两个节点都为空 → 继续下一轮
     * 3.2. 只有一个为空或值不同 → 返回 false
     * 3.3. 值相同 → 将它们的子节点按镜像顺序成对入队：
     * 3.3.1. node1.left 和 node2.right
     * 3.3.2. node1.right 和 node2.left
     * 4. 结束条件：队列为空时返回 true
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution2 {
        public boolean isSymmetric(TreeNode root) {
            // 需要用LinkedList实现，允许null值，ArrayDeque不允许null值
            Deque<TreeNode> q = new LinkedList<>();
            q.offer(root.left);
            q.offer(root.right);
            while (!q.isEmpty()) {
                TreeNode node1 = q.poll();
                TreeNode node2 = q.poll();
                if (node1 == null && node2 == null)
                    continue;
                if (node1 == null || node2 == null)
                    return false;
                if (node1.val != node2.val)
                    return false;
                q.offer(node1.left);
                q.offer(node2.right);
                q.offer(node1.right);
                q.offer(node2.left);
            }
            return true;
        }
    }

    /*
     * 思路一：递归法
     * 1. 定义辅助函数：设计一个函数 isMirror(tree1, tree2)，判断两棵树是否互为镜像
     * 2. 主函数调用：传入 root.left 和 root.right 进行镜像判断
     * 时间复杂度：O(n)，每个节点访问一次
     * 空间复杂度：O(h)，h 为树高，递归栈的深度
     * */
    class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            // 判断左子树和右子树是否为镜像
            return isMirror(root.left, root.right);
        }

        boolean isMirror(TreeNode tree1, TreeNode tree2) {
            // base case1：两棵树都为空 → 返回 true（对称）
            if (tree1 == null && tree2 == null)
                return true;
            // base case2：只有一棵为空 → 返回 false（不对称）
            if (tree1 == null || tree2 == null)
                return false;
            // base case3：两棵树的根节点值不同 → 返回 false
            if (tree1.val != tree2.val)
                return false;
            /*
             * 递归逻辑：
             * 1. 当前两个节点值相同 且
             * 2. tree1 的左子树与 tree2 的右子树镜像对称 且
             * 3. tree1 的右子树与 tree2 的左子树镜像对称
             * */
            return isMirror(tree1.left, tree2.right) && isMirror(tree1.right, tree2.left);
        }
    }


    public static void main(String[] args) {
        Solution solution = new SymmetricTree().new Solution();
        // put your test code here
        solution.isSymmetric(TreeNode.createRoot(new Integer[]{1, 2, 2, 3, 4, 4, 3}));
        System.out.println(solution.preorder(TreeNode.createRoot(new Integer[]{1, 2, 2, 3, 4, 4, 3})));
    }
}