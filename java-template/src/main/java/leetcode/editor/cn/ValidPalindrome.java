package leetcode.editor.cn;

public class ValidPalindrome {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双指针两端向中心判断字符是否相对
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param s 字符串
         * @return 是否是回文串
         */
//        public boolean isPalindrome(String s) {
//            int left = 0, right = s.length() - 1;
//            while (left < right) {
//                char l = s.charAt(left);
//                char r = s.charAt(right);
//                String sl = String.valueOf(l);
//                String sr = String.valueOf(r);
//                if (sl.matches("[^a-zA-Z0-9]")) {
//                    left++;
//                    continue;
//                }
//                if (sr.matches("[^a-zA-Z0-9]")) {
//                    right--;
//                    continue;
//                }
//                if (!sl.equalsIgnoreCase(sr))
//                    return false;
//                left++;
//                right--;
//            }
//            return true;
//        }
        public boolean isPalindrome(String s) {
            StringBuilder sb = new StringBuilder();
            // 先去掉非字母数字字符，并将字母转换为小写
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isLetterOrDigit(ch)) {
                    sb.append(Character.toLowerCase(ch));
                }
            }
            s = sb.toString();
            // 从两端向中心判断是否为回文串
            int left = 0, right = s.length() - 1;
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


    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
        // put your test code here
        String s = "A man, a plan, a canal: Panama";
        solution.isPalindrome(s);
    }
}