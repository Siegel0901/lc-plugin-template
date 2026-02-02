package leetcode.editor.cn;

import java.util.*;

public class SimplifyPath {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：
         * 1. 先将字符串以"/"分割得到split数组
         * 2. 遍历split数组元素加入中间数组
         * 2.1. "" 表明原字符是"/"，不加入
         * 2.2. "." 不加入
         * 2.3. ".." 不加入且删除最后一个元素（若中间数组为空则不删除）
         * 3. 中间数组用"/"连接得到结果字符串
         * 4. 返回"/" + 结果字符串
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param path 绝对路径
         * @return 简洁的规范路径
         */
        public String simplifyPath(String path) {
            String[] split = path.trim().split("/");
            LinkedList<String> toJoin = new LinkedList<>();

            for (String s : split) {
                if (s.isEmpty() || ".".equals(s))
                    continue;
                else if ("..".equals(s)) {
                    if (!toJoin.isEmpty())
                        toJoin.removeLast();
                    continue;
                }
                toJoin.addLast(s);
            }
            String res = String.join("/", toJoin);
            return "/" + res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SimplifyPath().new Solution();
        // put your test code here
//        System.out.println(solution.simplifyPath("/../"));
        System.out.println(String.join("/", new String[]{""}));
    }
}