package leetcode.editor.cn;

import java.util.*;

public class RemoveInvalidParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路四：BFS
     * 1. BFS层层扩展的特性保证了：首次找到的有效解即为最短路径
     * 2. 删除括号的个数即为BFS的层数，第一次找到的有效字符串，删除的括号数最少，只需收集同层中所有有效字符串即可
     * 3. 伪代码：
            currSet = {初始字符串}
            while true:
                nextSet = {}
                for each str in currSet:
                    检查 str 是否有效 → 若有效则收集
                    如果不是最终层，则删除一个括号生成新字符串，加入 nextSet
                if 找到有效字符串：
                    return 结果
                currSet = nextSet  // 进入下一层
     * 时间复杂度：O(n*2^n)
     * 空间复杂度：O(n*状态数)
     * */
    class Solution {
        public List<String> removeInvalidParentheses(String s) {
            // currSet记录当前层的字符串集合
            HashSet<String> currSet = new HashSet<>();
            currSet.add(s);
            // res记录最终结果
            List<String> res = new ArrayList<>();
            while (true) {
                // 遍历currSet中的每个字符串
                for (String str : currSet)
                    // 检查 str 是否有效
                    if (isValid(str))
                        // 若有效则收集
                        res.add(str);
                // 如果找到有效字符串，则返回结果
                if (!res.isEmpty())
                    return res;
                // 如果不是最终层，则删除一个括号生成新字符串，加入 nextSet
                currSet = getNextSet(currSet);
            }
        }

        private HashSet<String> getNextSet(HashSet<String> currSet) {
            HashSet<String> nextSet = new HashSet<>();
            for (String str : currSet) {
                for (int i = 0; i < str.length(); i++) {
                    // 跳过连续的相同括号，避免生成重复字符串
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1))
                        continue;
                    // 如果是括号，则删除该位置的括号，生成新字符串，加入 nextSet
                    if (str.charAt(i) == '(' || str.charAt(i) == ')')
                        // [0,i) + [i+1,str.length()) = 删除i位置的括号
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                }
            }
            return nextSet;
        }

        // 判断字符串是否有效
        private boolean isValid(String s) {
            int left = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') left++;
                else if (c == ')') {
                    if (left > 0) left--;
                    else return false;
                }
            }
            return left == 0;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路三：回溯 + 剪枝
     * 1. 通过预处理得到需要删除的左括号和右括号的数量lr和rr以及最大的括号匹配数max
     * 2. 回溯时剪枝：通过lr和rr来控制删除的个数，通过max来控制括号匹配的个数
     * 3. 提前获取最大字符串长度len = s.length() - lr - rr，只收集长度为len的字符串
     * 时间复杂度：O(n*2^n)
     * 空间复杂度：O(n)
     * */
    class Solution3 {
        Set<String> set = new HashSet<>();
        StringBuilder track = new StringBuilder();
        int max, len;

        public List<String> removeInvalidParentheses(String s) {
            // l记录左括号数量，r记录右括号数量
            int l = 0, r = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') l++;
                else if (c == ')') r++;
            }
            // max记录最大的括号匹配数
            max = Math.min(l, r);
            // lr记录需要删除的左括号数量，rr记录需要删除的右括号数量
            int lr = 0, rr = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') lr++;
                else if (c == ')') {
                    if (lr > 0) lr--;
                    else rr++;
                }
            }
            // len记录最大字符串长度
            len = s.length() - lr - rr;

            backtrack(s, 0, lr, rr, 0);
            return new ArrayList<>(set);
        }

        /**
         * 回溯+剪枝
         *
         * @param s    原始字符串
         * @param i    当前处理的位置
         * @param lr   左括号删除配额
         * @param rr   右括号删除配额
         * @param open track中待匹配括号数量
         */
        public void backtrack(String s, int i, int lr, int rr, int open) {
            // base case：删除配额不够以及待匹配括号超出范围
            if (lr < 0 || rr < 0 || open < 0 || open > max)
                return;
            // 到达字符串末尾，判断是否满足条件：删除配额用完且待匹配括号为零且长度为最大字符串长度
            if (i == s.length()) {
                if (lr == 0 && rr == 0 && open == 0 && track.length() == len)
                    set.add(track.toString());
                return;
            }
            char c = s.charAt(i);
            if (c == '(') {
                // 删除当前左括号，左括号删除配额减一
                backtrack(s, i + 1, lr - 1, rr, open);
                // 保留当前左括号，待匹配括号加一
                track.append(c);
                backtrack(s, i + 1, lr, rr, open + 1);
                track.deleteCharAt(track.length() - 1);
            } else if (c == ')') {
                // 删除当前右括号，右括号删除配额减一
                backtrack(s, i + 1, lr, rr - 1, open);
                // 保留当前右括号，待匹配括号减一
                track.append(c);
                backtrack(s, i + 1, lr, rr, open - 1);
                track.deleteCharAt(track.length() - 1);
            } else {
                track.append(c);
                backtrack(s, i + 1, lr, rr, open);
                track.deleteCharAt(track.length() - 1);
            }
        }
    }

    /*
     * 思路二：回溯 + 剪枝
     * 1. 什么时候需要剪枝？当扫描过程中的括号已经匹配了，此时出现右括号，则不必保留
     * 时间复杂度：O(n*2^n)
     * 空间复杂度：O(n*2^n)
     * */
    class Solution2 {
        StringBuilder track = new StringBuilder();
        List<String> res = new ArrayList<>();

        public List<String> removeInvalidParentheses(String s) {
            backtrack(s, 0, 0);
            int max = 0;
            for (String str : res)
                max = Math.max(max, str.length());
            HashSet<String> set = new HashSet<>();
            for (String str : res)
                if (str.length() == max)
                    set.add(str);
            return new ArrayList<>(set);
        }

        public void backtrack(String s, int i, int left) {
            if (i == s.length()) {
                if (isValid(track.toString()))
                    res.add(track.toString());
                return;
            }
            char c = s.charAt(i);
            if (c != '(' && c != ')') {
                track.append(c);
                backtrack(s, i + 1, left);
                track.deleteCharAt(track.length() - 1);
            } else {
                // 保留逻辑：当且仅当有多余左括号可以和右括号匹配的情况下，才考虑保留
                if (left > 0 || c == '(') {
                    // 做选择
                    track.append(c);
                    if (c == '(') left++;
                    else left--;
                    backtrack(s, i + 1, left);
                    // 撤销选择
                    track.deleteCharAt(track.length() - 1);
                    if (c == '(') left--;
                    else left++;
                }
                // 删除当前括号
                backtrack(s, i + 1, left);
            }
        }

        public boolean isValid(String s) {
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') count++;
                else if (c == ')') {
                    count--;
                    if (count < 0)
                        return false;
                }
            }
            return count == 0;
        }
    }

    /*
     * 思路一：暴力求解
     * 1. 回溯法列举出所有可能的字符串
     * 2. 判断字符串是否有效，同时记录有效字符串删除的步数，并存入HashMap
     * 3. 遍历HashMap，找到删除步数最少的有效字符串
     * 4. 时间复杂度：O(n*2^n)
     * 4.1. 回溯枚举：O(2^n)
     * 4.2. 判断字符串是否有效：O(n)
     * 4.3. 存入HashMap：O(n)
     * 5. 空间复杂度：O(n*2^n)
     * 5.1. 递归调用栈：O(n)
     * 5.2. StringBuilder：O(n)
     * 5.3. HashMap：O(n*2^n)：最坏存储2^n个字符串，每个字符串长度占O(n)
     * */
    class Solution1 {
        HashMap<String, Integer> strings = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {
            backtrack(s, 0, new StringBuilder(), 0);
            List<String> result = new ArrayList<>();
            // 最小删除步数
            int min = s.length();
            // 遍历HashMap，找到最小删除步数
            for (String string : strings.keySet()) {
                Integer delete = strings.get(string);
                min = Math.min(min, delete);
            }
            // 遍历HashMap，找到最小删除步数对应的有效字符串
            for (String string : strings.keySet())
                if (strings.get(string) == min)
                    result.add(string);
            // 返回结果
            return result;
        }

        /**
         * 回溯法列举所有可能的字符串
         *
         * @param s      原始字符串
         * @param i      当前处理的位置
         * @param track  路径
         * @param delete 删除的步数
         */
        public void backtrack(String s, int i, StringBuilder track, int delete) {
            // 所有的字符都处理完了
            if (i == s.length()) {
                String t = track.toString();
                // 判断字符串是否有效
                if (isValid(t))
                    // 将字符串加入HashMap，同时记录删除的步数
                    strings.put(t, delete);
                return;
            }
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                // 做选择
                track.append(c);
                backtrack(s, i + 1, track, delete);
                // 撤销选择，删除当前字符，删除步数加一
                track.deleteCharAt(track.length() - 1);
                backtrack(s, i + 1, track, delete + 1);
            } else {
                // 非括号字符直接添加
                track.append(c);
                backtrack(s, i + 1, track, delete);
                // 非括号字符处理完后需要从track中删除，不然后续撤销括号选择时会删除非括号字符导致错误
                track.deleteCharAt(track.length() - 1);
            }
        }

        /**
         * 判断字符串中的括号是否有效
         *
         * @param s 字符串
         * @return 是否有效
         */
        public boolean isValid(String s) {
            // 记录左括号的数量
            int count = 0;
            // 遍历字符串
            for (int i = 0; i < s.length(); i++) {
                // 如果是左括号，则计数加一
                if (s.charAt(i) == '(')
                    count++;
                    // 如果是右括号，则计数减一
                else if (s.charAt(i) == ')') {
                    count--;
                    // 如果计数小于零，则说明有多余的右括号无法被匹配
                    if (count < 0)
                        return false;
                }
            }
            // 如果计数等于零，则说明左括号和右括号能够匹配
            // 如果计数大于零，则说明有多余的左括号无法被匹配
            return count == 0;
        }
    }


    public static void main(String[] args) {
        Solution solution = new RemoveInvalidParentheses().new Solution();
        // put your test code here
        List<String> strings = solution.removeInvalidParentheses(")(f");
        strings.forEach(System.out::println);
    }
}