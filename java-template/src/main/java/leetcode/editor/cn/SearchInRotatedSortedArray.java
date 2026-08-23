package leetcode.editor.cn;

public class SearchInRotatedSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：一次二分查找
     * 1. nums分为左半段和右半段的两个有序区间，直接在nums上进行二分查找，
     * 2. 判断mid落在左半段还是右半段
     * 3. 在有序的那一段判断target是否在该有序区间中
     * 4. 根据判断结果收缩边界
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target)
                    return mid;
                // 判断mid落在左半段还是右半段
                if (nums[left] <= nums[mid]) {
                    // 左半段[left,mid]有序
                    if (nums[left] <= target && target < nums[mid])
                        // target∈[nums[left],nums[mid])
                        right = mid;        // target在右半段
                    else
                        // target∈(nums[mid],nums[right]]
                        left = mid + 1;     // target在左半段
                } else {
                    // 右半段[mid,right]有序
                    if (nums[mid] < target && target <= nums[right])
                        // target∈(nums[mid],nums[right]]
                        left = mid + 1;     // target在右半段
                    else
                        // target∈[nums[left],nums[mid])
                        right = mid;        // target在左半段
                }
            }
            return -1;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路：两次二分查找
     * 1. 找到分界点k
     * 2. 在[0,k)和[k,n)上进行二分查找
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution1 {
        public int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 1)
                return nums[0] == target ? 0 : -1;
            int k = 1;
            while (k < n && nums[k - 1] <= nums[k]) k++;
            int left = binarySearch(nums, target, 0, k);
            int right = binarySearch(nums, target, k, n);
            return left == -1 ? right : left;
        }

        int binarySearch(int[] nums, int target, int left, int right) {
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] < target)
                    left = mid + 1;
                else
                    right = mid;
            }
            return -1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
        // put your test code here
        System.out.println(solution.search(new int[]{1, 3}, 3));
    }
}