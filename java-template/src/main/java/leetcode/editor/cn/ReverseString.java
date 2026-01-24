package leetcode.editor.cn;

public class ReverseString {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：找到中间节点，使用双指针依次往外交换元素
         * 1. 若s的长度为奇数，则left和right初始值为s.length / 2
         * 2. 若s的长度为偶数，则left的初始值为s.length / 2 - 1，right的初始值为s.length / 2
         * 3. 交换s[left]和s[right]
         * 4. left--,right++，直至left为-1，right为s.length
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param s 字符数组
         */
//        public void reverseString(char[] s) {
//            int left = (s.length & 1) == 0 ? s.length / 2 - 1 : s.length / 2;
//            int right = s.length / 2;
//            char temp;
//            while (left > -1 && right < s.length) {
//                temp = s[left];
//                s[left--] = s[right];
//                s[right++] = temp;
//            }
//        }

        /**
         * 思路二：为啥要找中间节点？直接从两边向中间交换元素还不用判断奇偶
         * 1. left初值为0，right初值为s.length - 1
         * 2. 当left小于right时（未相遇），交换s[left]和s[right]
         * 时间复杂度：O(n)
         * 时间复杂度：O(1)
         *
         * @param s 字符数组
         */
        public void reverseString(char[] s) {
            int left = 0;
            int right = s.length - 1;
            char temp;
            while (left < right) {
                temp = s[left];
                s[left++] = s[right];
                s[right--] = temp;
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ReverseString().new Solution();
        // put your test code here
        solution.reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
    }
}