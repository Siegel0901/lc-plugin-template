package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class HammingDistance {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：异或
     * 1. 异或：二进制相同为0，不同为1
     * 2. 将x和y异或，结果二进制中1的个数，就是x和y二进制位不同的个数
     * */
    class Solution {
        public int hammingDistance(int x, int y) {
            int xor = x ^ y;
            int res = 0;
            while (xor != 0) {
                res += xor & 1;
                xor >>= 1;
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：除2取余
     * 1. 对x和y除2取余
     * 2. 统计余数中不同元素的位置
     * */
    class Solution1 {
        public int hammingDistance(int x, int y) {
            if (x == 0 && y == 0)
                return 0;
            Deque<Integer> xb = new ArrayDeque<>();
            Deque<Integer> yb = new ArrayDeque<>();
            while (x > 0) {
//                xb.offer(x % 2);
//                x /= 2;
                xb.offer(x & 1);
                x >>= 1;
            }
            while (y > 0) {
//                yb.offer(y % 2);
//                y /= 2;
                yb.offer(y & 1);
                y >>= 1;
            }
            int res = 0;
            // 从低位到高位判断相同位置元素是否相等
            while (!xb.isEmpty() && !yb.isEmpty())
                res += (xb.poll() != yb.poll() ? 1 : 0);
            // 剩余位置计算1的个数
            while (!xb.isEmpty()) res += xb.poll();
            while (!yb.isEmpty()) res += yb.poll();
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new HammingDistance().new Solution();
        // put your test code here

    }
}