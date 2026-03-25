package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> res = new ArrayList<>();
        List<String> track = new ArrayList<>();

        public List<List<String>> partition(String s) {
            backtrack(s, 0);
            return res;
        }

        void backtrack(String s, int start) {
            if (start == s.length()) {
                res.add(new ArrayList<>(track));
                return;
            }
            for (int i = start; i < s.length(); i++) {
                if (!isPalindrome(s, start, i))
                    continue;
                track.add(s.substring(start, i + 1));
                backtrack(s, i + 1);
                track.remove(track.size() - 1);
            }
        }

        boolean isPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left) != s.charAt(right))
                    return false;
                left++;
                right--;
            }
            return true;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        List<List<String>> res = new ArrayList<>();
        List<String> track = new ArrayList<>();

        public List<List<String>> partition(String s) {
            backtrack(s);
            return res;
        }

        void backtrack(String s) {
            if (s.isEmpty()) {
                res.add(new ArrayList<>(track));
                return;
            }
            for (int i = 1; i <= s.length(); i++) {
                String sub = s.substring(0, i);
                if (isPalindrome(sub)) {
                    track.add(sub);
                    backtrack(s.substring(i));
                    track.remove(track.size() - 1);
                }
            }
        }

        boolean isPalindrome(String s) {
            if (s == null)
                return false;
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right))
                    return false;
                left++;
                right--;
            }
            return true;
        }
    }


    public static void main(String[] args) {
        Solution solution = new PalindromePartitioning().new Solution();
        // put your test code here
        solution.partition("aab");
    }
}