package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class NAryTreePreorderTraversal {

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
        private final List<Integer> preorder = new ArrayList<>();

        private void traverse(Node root) {
            if (root == null) return;
            preorder.add(root.val);
            for (Node node : root.children) {
                traverse(node);
            }
        }

        public List<Integer> preorder(Node root) {
            preorder.clear();
            traverse(root);
            return preorder;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NAryTreePreorderTraversal().new Solution();
        // put your test code here

    }
}