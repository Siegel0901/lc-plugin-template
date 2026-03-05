package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNodeIi {

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
         * 思路二：层序遍历，不使用链表
         * 1. 用层序遍历得到每层的节点，在遍历过程中链接相邻节点
         */
        public Node connect(Node root) {
            Queue<Node> queue = new ArrayDeque<>();
            if (root == null)
                return null;
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                Node prev = null;
                for (int i = 0; i < size; i++) {
                    Node poll = queue.poll();
                    if (prev != null)
                        prev.next = poll;
                    prev = poll;
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
            }
            return root;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一：层序遍历
         * 1. 用层序遍历得到每层的节点，加入链表
         * 2. 遍历每层链表，链接相邻节点
         */
        public Node connect(Node root) {
            Queue<Node> queue = new ArrayDeque<>();
            if (root == null)
                return null;
            queue.offer(root);
            while (!queue.isEmpty()) {
                LinkedList<Node> level = new LinkedList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node poll = queue.poll();
                    level.add(poll);
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
                for (int i = 0; i < level.size() - 1; i++)
                    level.get(i).next = level.get(i + 1);
            }
            return root;
        }
    }


    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNodeIi().new Solution();
        // put your test code here

    }
}