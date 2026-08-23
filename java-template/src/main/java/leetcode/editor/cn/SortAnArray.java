package leetcode.editor.cn;

public class SortAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：快速排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     * */
    class Solution {
        public int[] sortArray(int[] nums) {
            Quick.sort(nums);
            return nums;
        }
    }

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
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SortAnArray().new Solution();
        // put your test code here

    }
}