package leetcode.editor.cn;

public class PopulatingNextRightPointersInEachNode {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    */

    class Solution {
        /**
         * 思路一：层序遍历
         * 1. 每个节点的next指向队头节点
         * 2. 若当前节点为该层最后一个节点，则next指向null
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param root 根节点
         * @return 结果
         */
//        public Node connect(Node root) {
//            if (root == null)
//                return null;
//            Queue<Node> queue = new ArrayDeque<>();
//            queue.offer(root);
//            while (!queue.isEmpty()) {
//                int levelSize = queue.size();
//                for (int i = 0; i < levelSize; i++) {
//                    Node poll = queue.poll();
//                    if (poll.left != null)
//                        queue.offer(poll.left);
//                    if (poll.right != null)
//                        queue.offer(poll.right);
//                    if (i == levelSize - 1)
//                        poll.next = null;
//                    else
//                        poll.next = queue.peek();
//                }
//            }
//            return root;
//        }

        /**
         * 思路二：抽象成三叉树
         * 1. 对满二叉树中除根节点外的每层的每两个节点视为同一个节点，则满二叉树变为一颗三叉树
         * 2.1.                        1
         * 2.2.                      (2 3)
         * 2.3.       (4 5)          (5 6)           (6 7)
         * 2.4. (8 9) (9 10) (10 11) (11 12) (12 13) (13 14) (14 15)
         * 3. 遍历三叉树，将三叉树中每个节点中的两个二叉树节点用next相连
         * 时间复杂度：O(n)
         * 时间复杂度：O(1)
         *
         * @param root 根节点
         * @return 结果
         */
        public Node connect(Node root) {
            if (root == null)
                return null;
            traverse(root.left, root.right);
            return root;
        }

        void traverse(Node node1, Node node2) {
            if (node1 == null || node2 == null)
                return;
            node1.next = node2;
            traverse(node1.left, node1.right);
            traverse(node1.right, node2.left);
            traverse(node2.left, node2.right);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNode().new Solution();
        // put your test code here

    }
}