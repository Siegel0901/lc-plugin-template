package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Queue;

public class VerifyPreorderSerializationOfABinaryTree {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路二：前序遍历反序列化
         */
        public boolean isValidSerialization(String preorder) {
            Queue<String> nodes = new ArrayDeque<>();
            for (String s : preorder.split(","))
                nodes.offer(s);
            // 若反序列化合法性验证完成后，还有剩余节点，则说明该序列不止一颗二叉树，不合法
            return deserialize(nodes) && nodes.isEmpty();
        }

        /**
         * 前序遍历反序列化合法性验证
         * 1. 空节点消耗一个#
         * 2. 非空节点必须消耗一个数字 + 左子树所需节点 + 右子树所需节点
         */
        boolean deserialize(Queue<String> nodes) {
            // 如果序列为空，但是还需要构建节点，则说明序列不合法
            if (nodes.isEmpty())
                return false;
            // 如果当前节点是"#",则表明为空节点，序列合法直接返回
            if ("#".equals(nodes.poll()))
                return true;
            // 当前节点是数字，为非空节点，则需要继续构建非空节点的左子树和右子树，验证其合法性
            return deserialize(nodes) && deserialize(nodes);
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一：利用二叉树的节点跟边的关系
         * 1. 非空节点：入度为1，出度为2
         * 2. 空节点：入度为1，出度为0
         */
        public boolean isValidSerialization(String preorder) {
            // 虚拟初始边指向根节点
            int edge = 1;
            for (String node : preorder.split(",")) {
                if ("#".equals(node)) {
                    if (--edge < 0) return false;
                } else {
                    if (--edge < 0) return false;
                    edge += 2;
                }
            }
            // 所有节点都应该对应着一条边
            return edge == 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new VerifyPreorderSerializationOfABinaryTree().new Solution();
        // put your test code here

    }
}