package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：快慢指针判断环形链表
     * 1. 把数组看成一个链表，下标i是节点，nums[i]是i指向的下一个节点
     * 2. 即i -> nums[i] -> nums[nums[i]] -> nums[nums[nums[i]]] -> ...
     * 3. 为什么数组中一定存在环？
     * 3.1. 数组长度为n+1，值域为[1,n]
     * 3.2. 鸽巢原理：将n+1个元素放入n个抽屉中，必有一个抽屉中包含多个元素
     * 3.3. 意味着两个不同的下标指向同一个节点，即存在环，环的入口即为重复的数字
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int findDuplicate(int[] nums) {
            // 初始化快慢指针
            int slow = 0;
            int fast = 0;
            // 移动指针，直到快慢指针相遇
            do {
                // 慢指针每次移动一步
                slow = nums[slow];
                // 快指针每次移动两步
                fast = nums[fast];
                fast = nums[fast];
                // 当快慢指针相遇时，存在环
            } while (slow != fast);
            // 找到环的入口
            // 初始化两个指针，一个从头节点开始，一个从相遇节点开始
            int ptr1 = 0;
            int ptr2 = slow;
            // 当两个指针相遇时，即为环的入口
            while (ptr1 != ptr2) {
                ptr1 = nums[ptr1];
                ptr2 = nums[ptr2];
            }
            /*
             * 证明：一个从头节点开始，一个从相遇节点开始，最终一定会在环的入口相遇
             * 1. 设头节点到环入口的距离为a
             * 2. 从环入口到快慢指针相遇点的距离为b
             * 3. 快慢指针相遇点到环入口的距离为c(即环的长度为b+c)
             * 慢指针走了a + b，快指针走了a + b + k(b + c)（多走了k圈）
             * 快指针速度是慢指针的2倍，则有：2(a + b) = a + b + k(b + c)
             * 化简：a + b = k(b + c)
             * 进一步化简：a = k(b + c) - b = kb + kc - b = (k-1)b + kc - c + c = (k-1)(b+c) + c
             * 即a = (k-1)*环长 + c，说明头节点到环入口的距离等于k圈环的长度加上从相遇点到环入口的距离
             * 这个等式说明：从起点走 a 步 和 从相遇点走 a 步，最终都会到达环入口！
             * ptr1 从起点出发，走 a 步到达环入口
             * ptr2 从相遇点出发，走 a 步 = 绕 k-1 圈 + 再走 c 步，也到达环入口
             * 两者每次各走一步，所以一定在环入口处相遇！
             * */
            return ptr1;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：哈希表
     * 1. 使用HashSet存储遍历过的数字
     * 2. 如果当前数字在HashSet中存在，则返回该数字
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        public int findDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num))
                    return num;
                else
                    set.add(num);
            }
            return -1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new FindTheDuplicateNumber().new Solution();
        // put your test code here

    }
}