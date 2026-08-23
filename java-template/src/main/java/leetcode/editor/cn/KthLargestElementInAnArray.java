package leetcode.editor.cn;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：快速排序变体——快速选择算法
     * 1. 从数组中随机选择一个元素作为枢轴
     * 2. 重新排列数组，大于枢轴的元素都移动到枢轴的左边，小于等于枢轴的元素都移动到枢轴的右边
     * 3. 如果枢轴的位置等于k，则返回枢轴
     * 4. 如果枢轴的位置大于k，则在枢轴的左边继续寻找
     * 5. 如果枢轴的位置小于k，则在枢轴的右边继续寻找
     * 时间复杂度：平均情况下为O(n)，最坏情况下为O(n^2)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            shuffle(nums);
            int l = 0, r = nums.length - 1;
            while (l <= r) {
                int p = partition(nums, l, r);
                if (p == k - 1)
                    return nums[p];
                else if (p > k - 1)
                    r = p - 1;
                else
                    l = p + 1;
            }
            return -1;
        }

        public int partition(int[] nums, int l, int r) {
            int pivot = nums[l];
            while (l < r) {
                while (l < r && nums[r] <= pivot) r--;
                nums[l] = nums[r];
                while (l < r && pivot < nums[l]) l++;
                nums[r] = nums[l];
            }
            nums[l] = pivot;
            return l;
        }

        public void shuffle(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 生成一个[i, n-1]的随机数
                int r = (int) (Math.random() * (n - i)) + i;
                swap(nums, i, r);
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 快速排序实现
     * 时间复杂度：平均情况下为O(nlogn)，最坏情况下为O(n^2)
     * 空间复杂度：O(logn)
     * */
    class Quick {
        public static void sort(int[] nums) {
            shuffle(nums);
            sort(nums, 0, nums.length - 1);
        }

        private static void sort(int[] nums, int l, int r) {
            if (l >= r)
                return;
            int p = partition(nums, l, r);
            sort(nums, l, p - 1);
            sort(nums, p + 1, r);
        }

        private static int partition(int[] nums, int l, int r) {
            int pivot = nums[l];
            while (l < r) {
                while (l < r && pivot < nums[r]) r--;
                nums[l] = nums[r];
                while (l < r && nums[l] <= pivot) l++;
                nums[r] = nums[l];
            }
            nums[l] = pivot;
            return l;
        }

        private static void shuffle(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 生成一个[i, n-1]的随机数
                int r = (int) (Math.random() * (n - i)) + i;
                swap(nums, i, r);
            }
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /*
     * 思路：优先队列
     * 1. 创建一个大小为k的优先队列
     * 2. 遍历数组，将元素加入优先队列
     * 3. 如果优先队列大小大于k，则将队列中最小的元素弹出
     * 4. 遍历结束后，优先队列中剩下的元素即为前k大的元素
     * 5. 返回优先队列中的最小元素即为第k大的元素
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(k)
     * */
    class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            // 默认小顶堆,堆顶为最小值
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            // 遍历数组，将元素加入优先队列
            for (int n : nums) {
                pq.add(n);
                // 如果优先队列大小大于k，则将队列中最小的元素弹出
                if (pq.size() > k)
                    pq.poll();
            }
            // 优先队列中剩下的元素即为前k大的元素
            return pq.peek();
        }
    }


    public static void main(String[] args) {
        Solution solution = new KthLargestElementInAnArray().new Solution();
        // put your test code here

    }
}