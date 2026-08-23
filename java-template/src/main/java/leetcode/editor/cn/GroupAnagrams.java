package leetcode.editor.cn;

import java.util.*;

public class GroupAnagrams {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：计数编码
     * 1. 遍历strs，对每个strs[i]中的每个字符进行计数作为key
     * 2. 将key和对应的strs[i]存入Map<String,List<String>>中
     * 时间复杂度：O(n*k)，n为strs.length，k为strs[i].length
     * 空间复杂度：O(n*k)
     * 理论上计数法是 O(n×k)，比排序法的 O(n×k log k) 更优。
     * 但在实际测试中，由于 Java 的 Arrays.sort 对基本类型做了高度优化，且题目中字符串长度较短，排序法的常数因子更小，实际运行更快。
     * 如果字符串非常长（如超过1000字符），计数法的优势才会体现出来。
     * */
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                String key = encode(str);
                // 检查key是否存在，返回已有或新建的list，加入str
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(map.values());
        }

        String encode(String str) {
            int[] count = new int[26];
            for (char c : str.toCharArray())
                count[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    sb.append('a' + i);
                    sb.append(count[i]);
                }
            }
            return sb.toString();
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：排序编码
     * 1. 遍历strs，对每个strs[i]排序
     * 2. 排序后作为key存入Map<String,List<String>>中
     * 时间复杂度：O(n*klogk)，n为strs.length，k为strs[i].length
     * 空间复杂度：O(n*k)
     * */
    class Solution1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                // 转为数组
                char[] chs = str.toCharArray();
                // 排序
                Arrays.sort(chs);
                // 转为字符串
                String key = new String(chs);
                // 检查key是否存在，返回已有或新建的list，加入str
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }


    public static void main(String[] args) {
        Solution solution = new GroupAnagrams().new Solution();
        // put your test code here

    }
}