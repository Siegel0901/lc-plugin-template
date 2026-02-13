package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class NextGreaterElementI {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：单调栈 + 双重for循环遍历
         * 1. 利用单调栈找出num2的每个元素的下一个更大元素构成数组res
         * 2. 遍历num1，在num2中找到对应元素下标j，返回res[j]构成的ans
         * 时间复杂度：O(n*m)
         * 空间复杂度：O(n)
         *
         * @param nums1 数组1
         * @param nums2 数组2
         * @return 结果数组
         */
//        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
//            int m = nums1.length;
//            int n = nums2.length;
//            int[] ans = new int[m];
//            int[] res = new int[n];
//            ArrayDeque<Integer> stk = new ArrayDeque<>();
//            // 逆序遍历，栈内表示当前元素后的元素（即找下一个）
//            for (int i = n - 1; i >= 0; i--) {
//                // 弹出比当前元素小的元素（即找更大）
//                while (!stk.isEmpty() && stk.peek() <= nums2[i])
//                    stk.pop();
//                // 记录下一个更大元素
//                res[i] = stk.isEmpty() ? -1 : stk.peek();
//                // 当前元素入栈给前面的元素做参考
//                stk.push(nums2[i]);
//            }
//            // 遍历num1，在num2中找到对应元素下标j，返回res[j]构成的ans
//            for (int i = 0; i < m; i++)
//                for (int j = 0; j < n; j++)
//                    if (nums2[j] == nums1[i])
//                        ans[i] = res[j];
//            return ans;
//        }

        /**
         * 思路二：单调栈 + Map映射
         * 1. 利用单调栈找出num2的每个元素的下一个更大元素构成数组res O(n)
         * 2. 遍历num2，将num2[i] -> res[i]作为映射存入Map O(n)
         * 3. 遍历num1，num1[i]作为key查找对应的下一个更大元素构成结果数组ans O(m)
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums1 数组1
         * @param nums2 数组2
         * @return 结果数组
         */
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int[] ans = new int[m];
            int[] res = new int[n];
            ArrayDeque<Integer> stk = new ArrayDeque<>();
            Map<Integer, Integer> map = new HashMap<>();
            // 逆序遍历，栈内表示当前元素后的元素（即找下一个）
            for (int i = n - 1; i >= 0; i--) {
                // 弹出比当前元素小的元素（即找更大）
                while (!stk.isEmpty() && stk.peek() <= nums2[i])
                    stk.pop();
                // 记录下一个更大元素
                res[i] = stk.isEmpty() ? -1 : stk.peek();
                // 当前元素作为key，下一个更大元素作为value，存入map
                map.put(nums2[i], res[i]);
                // 当前元素入栈给前面的元素做参考
                stk.push(nums2[i]);
            }
            // 遍历num1，在map中找到对应的下一个更大元素
            for (int i = 0; i < m; i++)
                ans[i] = map.get(nums1[i]);
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NextGreaterElementI().new Solution();
        // put your test code here

    }
}