package leetcode.editor.cn;

public class BulbSwitcher {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：数学规律
         * 1. 奇数次操作，灯打开；偶数次操作，灯关闭
         * 2. 一个灯的位置如果能被轮次整除，就会被操作一次。
         * 3. 所以要找一个位置，它的因子个数是奇数。
         * 4. 而一个数可以分为两个因子相乘，要满足因子个数为奇数，则这两个因子要相等。
         * 5. 所以要看小于等于n的数中，有几个完全平方数
         */
        public int bulbSwitch(int n) {
            return (int) Math.sqrt(n);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BulbSwitcher().new Solution();
        // put your test code here

    }
}