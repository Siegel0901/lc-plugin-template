package leetcode.editor.cn;

import leetcode.editor.common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    /**
     * 思路：
     * 1. 序列不包含null值：
     * 1.1. 只给出一种遍历顺序，无法还原唯一的二叉树
     * 1.2. 给出两种遍历顺序：
     * 1.2.1. 前序/后序 + 中序 可以还原唯一二叉树
     * 1.2.2. 前序 + 后序 无法还原唯一的二叉树
     * 2. 序列包含null值，且只给出一种遍历顺序
     * 2.1. 前序/后序 可以还原唯一二叉树
     * 2.2. 中序 无法还原唯一二叉树
     */
    public class Codec {

        // Encodes a tree to a single string.
//        public String serialize(TreeNode root) {
//            StringBuilder sb = new StringBuilder();
//            _serialize(root, sb);
//            return sb.toString();
//        }

        String SEP = ",";
        String NULL = "#";

//        void _serialize(TreeNode root, StringBuilder sb) {
//            if (root == null) {
//                sb.append(NULL).append(SEP);
//                return;
//            }
//            /*
//             * 前序位置
//             * 序列化结果：1,[2,#,#,][3,4,#,#,5,#,#,]
//             * */
//            // sb.append(root.val).append(SEP);
//            _serialize(root.left, sb);
//            _serialize(root.right, sb);
//            /*
//             * 后序位置
//             * 序列化结果：[#,#,2,][#,#,4,#,#,5,3,]1,
//             * */
//            sb.append(root.val).append(SEP);
//        }

        // Decodes your encoded data to tree.
//        public TreeNode deserialize(String data) {
//            Deque<String> nodes = new ArrayDeque<>(Arrays.asList(data.split(SEP)));
//            return _deserialize(nodes);
//        }

        /**
         * 前序遍历反序列化
         * 序列化结果：1,[2,#,#,][3,4,#,#,5,#,#,]
         * 第一个元素为根节点，先构造左子树，再构造右子树
         */
//        TreeNode _deserialize(Deque<String> nodes) {
//            if (nodes.isEmpty())
//                return null;
//            String poll = nodes.poll();
//            if (NULL.equals(poll))
//                return null;
//            TreeNode root = new TreeNode(Integer.parseInt(poll));
//            root.left = _deserialize(nodes);
//            root.right = _deserialize(nodes);
//            return root;
//        }

        /**
         * 后序遍历反序列化
         * 序列化结果：[#,#,2,][#,#,4,#,#,5,3,]1,
         * 逆序遍历序列化结果，最后一个元素为根节点，先构造右子树，后构造左子树
         */
//        TreeNode _deserialize(Deque<String> nodes) {
//            if (nodes.isEmpty())
//                return null;
//            String poll = nodes.pollLast();
//            if (NULL.equals(poll))
//                return null;
//            TreeNode root = new TreeNode(Integer.parseInt(poll));
//            root.right = _deserialize(nodes);
//            root.left = _deserialize(nodes);
//            return root;
//        }

        /**
         * 层级遍历序列化
         * 序列化结果：1,2,3,#,#,4,5,#,#,#,#,
         */
        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            // 使用LinkedList，可以传入null值
            Queue<TreeNode> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll == null) {
                        sb.append(NULL).append(SEP);
                        continue;
                    }
                    sb.append(poll.val).append(SEP);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            return sb.toString();
        }

        /**
         * 层级遍历反序列化
         * 序列化结果：1,2,3,#,#,4,5,#,#,#,#,
         * 层级遍历的反序列化仍然需要先构造根节点，再构造左右子节点
         * 构造的过程仍然是层级遍历，用index可以表示当前节点及其左右子节点在层级遍历结果中的位置
         */
        public TreeNode deserialize(String data) {
            if (data.isEmpty())
                return null;
            String[] nodes = data.split(SEP);
            int index = 0;
            TreeNode root = new TreeNode(Integer.parseInt(nodes[index++]));
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode parent = queue.poll();
                    String left = nodes[index++];
                    if (!NULL.equals(left)) {
                        parent.left = new TreeNode(Integer.parseInt(left));
                        queue.offer(parent.left);
                    }
                    String right = nodes[index++];
                    if (!NULL.equals(right)) {
                        parent.right = new TreeNode(Integer.parseInt(right));
                        queue.offer(parent.right);
                    }
                }
            }
            return root;
        }

    }

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Codec solution = new SerializeAndDeserializeBinaryTree().new Codec();
        // put your test code here
        String data = solution.serialize(TreeNode.createRoot(new Integer[]{1, 2, 3, null, null, 4, 5}));
        TreeNode res = solution.deserialize(data);
    }
}