package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class NAryTreePostorderTraversal {

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
        private List<Integer> postorder = new ArrayList<>();

        private void traverse(Node root) {
            if (root == null) return;
            root.children.forEach(this::traverse);
            postorder.add(root.val);
        }

        public List<Integer> postorder(Node root) {
            postorder.clear();
            traverse(root);
            return postorder;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NAryTreePostorderTraversal().new Solution();
        // put your test code here

    }
}