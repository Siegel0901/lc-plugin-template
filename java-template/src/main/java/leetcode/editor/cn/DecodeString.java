package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：栈模拟递归
     * 1. 遇到[就把当前状态保存到栈中，遇到]就恢复上一层状态
     * 2. 需要两个栈：cntStk保存倍速times，strStk保存进入[之前的字符串
     * 3. 遍历每个字符
     * 3.1. 字母 -> 拼到当前sb
     * 3.2. 数字 -> 解析完整数字times
     * 3.3. '[' -> 把times和sb压栈,sb清零,准备解析新层
     * 3.4. ']' -> 弹出times和上层的sb,把当前sb重复times,拼回上层sb
     * 时间复杂度：O(n + L)
     * 空间复杂度：O(L + d)
     */
    class Solution {
        public String decodeString(String s) {
            // 当前层的字符串
            StringBuilder sb = new StringBuilder();
            // cntStk保存倍数times，strStk保存进入[之前的字符串
            Deque<Integer> cntStk = new ArrayDeque<>();
            Deque<StringBuilder> strStk = new ArrayDeque<>();
            int times = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // 字母
                if (Character.isLetter(c))
                    sb.append(c);
                // 数字
                else if (Character.isDigit(c))
                    times = times * 10 + (c - '0');
                else if (c == '[') {
                    // 把当前层的状态保存到栈中
                    cntStk.push(times);
                    strStk.push(sb);
                    // 准备解析新层
                    sb = new StringBuilder();
                    times = 0;
                } else {
                    // 弹出上层状态
                    int cnt = cntStk.pop();
                    StringBuilder prev = strStk.pop();
                    // 拼回上层字符串
                    prev.append(sb.toString().repeat(cnt));
                    sb = prev;
                }
            }
            return sb.toString();
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：递归
     * 1. 递归函数要做什么？
     * 1.1. 解析一段字符串，返回解析后的结果
     * 1.2. 需要一个全局指针i来记录当前扫描到哪个字符
     * 2. 扫描时遇到不同的字符怎么处理？
     * 2.1. 字母：直接拼到结果里
     * 2.2. 数字：解析出完整的数字times，然后处理后面的[...]
     * 2.3. [：递归解析[里面的内容
     * 2.4. ]：递归出口，告诉上层[...]解析完了
     * 3. 复杂度分析：
     * 时间复杂度
     * O(n + L)，其中 L 是解码后字符串的长度。
     * 算法会遍历输入字符串的每个字符一次（数字、字母、括号），这部分是 O(n)（n 为输入长度）。
     * 每次遇到 [ 时递归处理内部子串，但内部子串只被解析一次，不会重复扫描。
     * 重复操作 inner.repeat(times) 会生成 inner.length() * times 个字符，总复制次数恰好等于最终输出长度 L。
     * 因此，整体时间由输出字符串的大小决定，即 O(L)。
     * 空间复杂度
     * O(L + d)，其中 d 为最大嵌套深度（d ≤ n），通常可简化为 O(L)。
     * 递归栈：最大深度为括号嵌套层数 d，占用 O(d) 空间。
     * 字符串缓存：每一层递归都有一个 StringBuilder，保存当前层已解析的部分结果。在深度递归时，所有层的 StringBuilder 累计存储的字符总数不会超过最终输出长度 L。
     * 此外，inner.repeat(times) 会临时创建重复后的字符串，峰值内存可能额外增加 O(L)，但仍在 O(L) 量级内。
     * 因此总空间复杂度为 O(L + d)，由于 L 通常远大于 d，也可记为 O(L)。
     * 若输入极端（如 "1[1[1[...]]]" 嵌套很深但输出很短），空间复杂度主要由递归深度决定，此时为 O(d)（≤ O(n)），但整体仍可安全表述为 O(L + n)。
     */
    class Solution1 {
        int i = 0;

        public String decodeString(String s) {
            StringBuilder sb = new StringBuilder();
            while (i < s.length()) {
                char c = s.charAt(i);
                if (Character.isLetter(c)) {
                    sb.append(c);
                    i++;
                } else if (Character.isDigit(c)) {
                    int times = 0;
                    // 解析完整数字
                    while (Character.isDigit(s.charAt(i)))
                        times = times * 10 + (s.charAt(i++) - '0');
                    i++;    // 跳过 '['
                    String inner = decodeString(s);
                    sb.append(inner.repeat(times));
                } else {
                    i++;    // 消费 ']'，当前递归层结束
                    return sb.toString();
                }
            }
            return sb.toString();
        }
    }


    public static void main(String[] args) {
        Solution solution = new DecodeString().new Solution();
        // put your test code here
        System.out.println(solution.decodeString("3[a2[c]]"));
    }
}