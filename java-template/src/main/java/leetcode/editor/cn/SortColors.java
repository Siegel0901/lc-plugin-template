package leetcode.editor.cn;

public class SortColors {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：将相同元素提取出来放到对应数组中
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         */
//        public void sortColors(int[] nums) {
//            int[] red = Arrays.stream(nums).filter(i -> i == 0).toArray();
//            int[] white = Arrays.stream(nums).filter(i -> i == 1).toArray();
//            int[] blue = Arrays.stream(nums).filter(i -> i == 2).toArray();
//            System.arraycopy(red, 0, nums, 0, red.length);
//            System.arraycopy(white, 0, nums, red.length, white.length);
//            System.arraycopy(blue, 0, nums, red.length + white.length, blue.length);
//        }

        /**
         * 思路二：遍历数组，将0放到结果数组开头，2放到结果数组末尾，剩余位置放1
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         */
//        public void sortColors(int[] nums) {
//            int[] res = new int[nums.length];
//            int left = 0, right = nums.length - 1;
//            for (int num : nums) {
//                if (num == 0)
//                    res[left++] = 0;
//                if (num == 2)
//                    res[right--] = 2;
//            }
//            for (; left <= right; left++)
//                res[left] = 1;
//            System.arraycopy(res, 0, nums, 0, nums.length);
//        }

        /**
         * 思路三：计数排序
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         */
//        public void sortColors(int[] nums) {
//            int red = 0, white = 0, blue = 0;
//            for (int num : nums) {
//                if (num == 0) red++;
//                if (num == 1) white++;
//                if (num == 2) blue++;
//            }
//            int index = 0;
//            for (int i = 0; i < red; i++)
//                nums[index++] = 0;
//            for (int i = 0; i < white; i++)
//                nums[index++] = 1;
//            for (int i = 0; i < blue; i++)
//                nums[index++] = 2;
//        }

        /**
         * 思路三：双指针删除重复元素的思路
         * 1. 先删除元素2，对剩余元素赋值为2：[0,1,1,0,2,2]
         * 2. 对剩余区域再删除元素1，对剩余元素赋值为1：[0,0,1,1,2,2]
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         */
//        public void sortColors(int[] nums) {
//            int index = 0;
//            for (int i = 0; i < nums.length; i++)
//                if (nums[i] != 2)
//                    nums[index++] = nums[i];
//            for (int i = index; i < nums.length; i++)
//                nums[i] = 2;
//
//            int index2 = 0;
//            for (int i = 0; i < index; i++)
//                if (nums[i] != 1)
//                    nums[index2++] = nums[i];
//            for (int i = index2; i < index; i++)
//                nums[i] = 1;
//        }

        /**
         * 思路四：三指针一次遍历
         * 1. 维护0和2的区间指针p0初值为0，p2初值为nums.length - 1
         * 2. p指针遍历nums
         * 2.1. 若为0，则与p0交换，p和p0后移
         * 2.2. 若为2，则与p2交换，p2前移
         * 2.3. 若为1，则p后移
         * 3. 当p超过p2时，处理完毕
         * 4. 0区: [0, p0), 1区: [p0, p), 未处理区: [p, p2], 2区: (p2, nums.length - 1]
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         */
        public void sortColors(int[] nums) {
            int p0 = 0, p2 = nums.length - 1, p = 0;
            // 未处理区: [p, p2]
            while (p <= p2) {
                // 处理0
                if (nums[p] == 0)
                    // p0的位置要么是0，要么是1
                    // 是0则已经处理过了，p需要后移，否则num[p]一直是0
                    // 是1则p后移
                    swap(nums, p0++, p++);
                // 处理2
                else if (nums[p] == 2)
                    // p2属于未处理区: [p, p2]，故不移动p
                    swap(nums, p, p2--);
                else
                    p++;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SortColors().new Solution();
        // put your test code here
        int[] nums = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
    }
}