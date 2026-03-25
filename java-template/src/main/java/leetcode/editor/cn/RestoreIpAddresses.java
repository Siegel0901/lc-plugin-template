package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> res = new ArrayList<>();
        List<String> track = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            backtrack(s, 0);
            return res;
        }

        // 回溯算法框架
        void backtrack(String s, int start) {
            // base case，走到叶子节点
            // 即整个 s 被成功分割为合法的四部分，记下答案
            if (start == s.length() && track.size() == 4)
                res.add(String.join(".", track));
            for (int i = start; i < s.length(); i++) {
                // s[start..i] 不是合法的 ip 数字，不能分割
                if (!isValid(s, start, i))
                    continue;
                // 已经分解成 4 部分了，不能再分解了
                if (track.size() >= 4)
                    break;
                // s[start..i] 是一个合法的 ip 数字，可以进行分割
                // 做选择，把 s[start..i] 放入路径列表中
                track.add(s.substring(start, i + 1));
                // 进入回溯树的下一层，继续切分 s[i+1..]
                backtrack(s, i + 1);
                // 撤销选择
                track.remove(track.size() - 1);
            }
        }

        // 判断 s[start..end] 是否是一个合法的 ip 段
        boolean isValid(String s, int start, int end) {
            int length = end - start + 1;

            if (length == 0 || length > 3)
                return false;

            // 如果只有一位数字，肯定是合法的
            if (length == 1)
                return true;

            // 多于一位数字，但开头是 0，肯定不合法
            if (s.charAt(start) == '0')
                return false;

            // 排除了开头是 0 的情况，那么如果是两位数，怎么着都是合法的
            if (length == 2)
                return true;

            // 现在输入的一定是三位数
            // 不可能大于 255
            return Integer.parseInt(s.substring(start, start + length)) <= 255;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RestoreIpAddresses().new Solution();
        // put your test code here

    }
}