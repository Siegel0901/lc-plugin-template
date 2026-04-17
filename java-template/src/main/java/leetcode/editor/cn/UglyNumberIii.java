package leetcode.editor.cn;

public class UglyNumberIii {
    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路三：二分查找 + 容斥原理
     * 1. 在[1, min(a,b,c)*n]范围内二分查找第n个丑数
     * 2. 对于任意数mid，计算[1,mid]范围内有多少个丑数(能被a或b或c整除的数)
     * 3. 使用容斥原理：count = mid/a + mid/b + mid/c - mid/lcm(a,b) - mid/lcm(a,c) - min/lcm(b,c) + mid/lcm(a,lcm(b,c))
     * 4. 容斥原理核心思想：先全部加上，再减去重复的，最后补回多减的
     * 5.1. a的倍数，b的倍数，c的倍数都是丑数
     * 5.2. 一共有mid个丑数
     * 5.3. a的倍数有mid/a个，b的倍数有mia/b个，c的倍数有mid/c个
     * 5.4. 既是a的倍数又是b的倍数有mid/lcm(a,b)个
     * 5.5. 既是a的倍数又是c的倍数有mid/lcm(a,c)个
     * 5.6. 既是b的倍数又是c的倍数有mid/lcm(b,c)个
     * 5.7. 既是a的倍数又是b的倍数又是c的倍数有mid/lcm(a,lcm(b,c))个
     * 时间复杂度：O(log(n*max(a,b,c)))
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int nthUglyNumber(int n, int a, int b, int c) {
            long ab = lcm(a, b);
            long ac = lcm(a, c);
            long bc = lcm(b, c);
            long abc = lcm(a, lcm(b, c));
            long left = 1;
            long right = (long) Math.max(a, Math.min(b, c)) * n;
            // 二分查找[left,right)
            while (left < right) {
                long mid = left + (right - left) / 2;
                // 查找[1,mid]中有多少个丑数
                long count = mid / a + mid / b + mid / c
                        - mid / ab - mid / ac - mid / bc
                        + mid / abc;
                if (count < n)
                    // 丑数不够，在[mid+1,right)中二分
                    left = mid + 1;
                else
                    // 丑数多了，在[left,mid)中二分
                    right = mid;
            }
            // 最后夹出来的个数就是丑数个数
            return (int) left;
        }

        long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        long lcm(long a, long b) {
            return a / gcd(a, b) * b;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /*
     * 思路二：合并链表
     * 1. 合并三条链表：a的倍数链表，b的倍数链表，c的倍数链表
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     * */
    class Solution2 {
        public int nthUglyNumber(int n, int a, int b, int c) {
            // 丑数倍数链表当前节点的值
            long producta = a, productb = b, productc = c;
            // 丑数序列的最后一个丑数
            long ugly = 0;
            // 丑数序列索引
            int p = 1;
            while (p <= n) {
                // 取最小的链表节点
                ugly = Math.min(Math.min(producta, productb), productc);
                p++;
                if (ugly == producta)
                    producta += a;
                if (ugly == productb)
                    productb += b;
                if (ugly == productc)
                    productc += c;
            }
            return (int) ugly;
        }
    }

    /*
     * 思路一：合并链表
     * 1. 合并三条链表：a的倍数链表，b的倍数链表，c的倍数链表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 【Memory Limit Exceeded】
     * */
    class Solution1 {
        public int nthUglyNumber(int n, int a, int b, int c) {
            // 丑数倍数链表指针
            long pa = 1, pb = 1, pc = 1;
            // 丑数倍数链表当前节点的值
            long producta = 1, productb = 1, productc = 1;
            // 丑数序列前两位为0和1
            int[] ugly = new int[n + 2];
            // 丑数序列索引
            int p = 1;
            while (p <= n + 1) {
                // 取最小的链表节点
                long min = Math.min(Math.min(producta, productb), productc);
                // 加入丑数序列
                ugly[p++] = (int) min;
                if (min == producta)
                    producta = a * pa++;
                if (min == productb)
                    productb = b * pb++;
                if (min == productc)
                    productc = c * pc++;
            }
            return ugly[n + 1];
        }
    }


    public static void main(String[] args) {
        Solution solution = new UglyNumberIii().new Solution();
        // put your test code here
//        System.out.println(solution.nthUglyNumber(3, 2, 3, 5));
        System.out.println(solution.nthUglyNumber(4, 2, 3, 4));
    }
}