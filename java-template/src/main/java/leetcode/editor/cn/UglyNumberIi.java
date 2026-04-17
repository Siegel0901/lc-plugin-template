package leetcode.editor.cn;

public class UglyNumberIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：筛丑数+合并链表
     * 1. 将丑数分为2的倍数，3的倍数，5的倍数三条链表
     * 2. 合并三条链表，第n个元素即为答案
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 模拟：
     * 初始化：
     *         ugly[] 数组（索引从1开始）
     *         p2=1, p3=1, p5=1（三个链表的指针）
     *         product2=1, product3=1, product5=1（三个链表当前指向的值）
     *  第1轮 (p=1)：
     *         min = min(1, 1, 1) = 1
     *         ugly[1] = 1
     *         ugly = {1}
     *         三个都等于min，所以都更新：
     *         product2 = 2 * ugly[1] = 2, p2=2
     *         product3 = 3 * ugly[1] = 3, p3=2
     *         product5 = 5 * ugly[1] = 5, p5=2
     * 第2轮 (p=2)：
     *         min = min(2, 3, 5) = 2
     *         ugly[2] = 2
     *         ugly = {1,2}
     *         只有 product2 == min：
     *         product2 = 2 * ugly[2] = 4, p2=3
     * 第3轮 (p=3)：
     *         min = min(4, 3, 5) = 3
     *         ugly[3] = 3
     *         ugly = {1,2,3}
     *         只有 product3 == min：
     *         product3 = 3 * ugly[2] = 6, p3=3
     * 第4轮 (p=4)：
     *         min = min(4, 6, 5) = 4
     *         ugly[4] = 4
     *         ugly = {1,2,3,4}
     *         只有 product2 == min：
     *         product2 = 2 * ugly[3] = 6, p2=4
     * 第5轮 (p=5)：
     *         min = min(6, 6, 5) = 5
     *         ugly[5] = 5
     *         ugly = {1,2,3,4,5}
     *         只有 product5 == min：
     *         product5 = 5 * ugly[2] = 10, p5=3
     * 第6轮 (p=6)：
     *         min = min(6, 6, 10) = 6
     *         ugly[6] = 6
     *         ugly = {1,2,3,4,5,6}
     *         product2 和 product3 都等于6，都要更新（去重关键！）：
     *         product2 = 2 * ugly[4] = 8, p2=5
     *         product3 = 3 * ugly[3] = 9, p3=4
     * 第7轮 (p=7)：
     *         min = min(8, 9, 10) = 8
     *         ugly[7] = 8
     *         ugly = {1,2,3,4,5,6,8}
     *         只有 product2 == min：
     *         product2 = 2 * ugly[5] = 10, p2=6
     * 第8轮 (p=8)：
     *         min = min(10, 9, 10) = 9
     *         ugly[8] = 9
     *         ugly = {1,2,3,4,5,6,8,9}
     *         只有 product3 == min：
     *         product3 = 3 * ugly[4] = 12, p3=5
     * 第9轮 (p=9)：
     *         min = min(10, 12, 10) = 10
     *         ugly[9] = 10
     *         ugly = {1,2,3,4,5,6,8,9,10}
     *         product2 和 product5 都等于10，都要更新：
     *         product2 = 2 * ugly[6] = 12, p2=7
     *         product5 = 5 * ugly[3] = 15, p5=4
     * 第10轮 (p=10)：
     *         min = min(12, 12, 15) = 12
     *         ugly[10] = 12
     *         ugly = {1,2,3,4,5,6,8,9,10,12}
     *         product2 和 product3 都等于12，都要更新：
     *         product2 = 2 * ugly[7] = 16, p2=8
     *         product3 = 3 * ugly[5] = 15, p3=6
     * 最终结果： ugly[10] = 12
     * 丑数序列： 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 ✓
     * 关键点：
     *      用 if 而不是 else if，因为当多个链表当前值相同时（如第6轮的6），需要同时推进多个指针，避免重复加入丑数序列
     *
     * */
    class Solution {
        public int nthUglyNumber(int n) {
            // 丑数倍数链表指针
            int p2 = 1, p3 = 1, p5 = 1;
            // 丑数倍数链表当前节点的值
            int product2 = 1, product3 = 1, product5 = 1;
            // 丑数序列
            int[] ugly = new int[n + 1];
            // 丑数序列索引
            int p = 1;
            while (p <= n) {
                // 取最小的链表节点
                int min = Math.min(Math.min(product2, product3), product5);
                // 加入丑数序列
                ugly[p++] = min;
                /*
                 * 丑数序列:     1   2   3   4   5
                 * 2倍数链表     1   2   4   6   8
                 * 3倍数链表     1   3   6   9   12
                 * 5倍数链表     1   5   10  15  20
                 * */
                if (min == product2)
                    product2 = 2 * ugly[p2++];
                if (min == product3)
                    product3 = 3 * ugly[p3++];
                if (min == product5)
                    product5 = 5 * ugly[p5++];
            }
            return ugly[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new UglyNumberIi().new Solution();
        // put your test code here

    }
}