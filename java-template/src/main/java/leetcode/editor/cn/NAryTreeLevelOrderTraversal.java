package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NAryTreeLevelOrderTraversal {

    //leetcode submit region begin(Prohibit modification and deletion)
/*
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
*/

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> result = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            if (root == null) return result;
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> layer = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node cur = queue.poll();
                    layer.add(cur.val);
                    cur.children.forEach(queue::offer);
                }
                result.add(layer);
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NAryTreeLevelOrderTraversal().new Solution();
        // put your test code here

    }
}