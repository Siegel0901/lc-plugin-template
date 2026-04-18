package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：nSumTarget模板
     * 时间复杂度：O(n^(n-1)))
     * 空间复杂度：O(1)
     * */
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            return nSumTarget(nums, 3, 0, 0);
        }

        List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
            int sz = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            // base case1
            if (n < 2 || sz < n)
                return res;
            // base case2
            if (n == 2) {
                int l = start, r = sz - 1;
                while (l < r) {
                    int left = nums[l], right = nums[r];
                    int sum = left + right;
                    if (sum < target)
                        while (l < r && nums[l] == left) l++;
                    else if (target < sum)
                        while (l < r && nums[r] == right) r--;
                    else {
                        res.add(new ArrayList<>(List.of(left, right)));
                        while (l < r && nums[l] == left) l++;
                        while (l < r && nums[r] == right) r--;
                    }
                }
            } else {
                // n>2时，递归计算(n-1)Sum
                for (int i = start; i < sz; i++) {
                    List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                    for (List<Integer> arr : sub) {
                        arr.add(nums[i]);
                        res.add(arr);
                    }
                    while (i < sz - 1 && nums[i] == nums[i + 1]) i++;
                }
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：先固定第一个数字，剩下的两个数字用twoSum解决
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * */
    class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {
            return threeSumTarget(nums, 0);
        }

        List<List<Integer>> threeSumTarget(int[] nums, int target) {
            Arrays.sort(nums);
            int n = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<List<Integer>> tuples = twoSumTarget(nums, i + 1, target - nums[i]);
                for (List<Integer> tuple : tuples) {
                    tuple.add(nums[i]);
                    res.add(tuple);
                }
                /*
                 * 跳过第一个数字重复的情况,否则会出现重复结果
                 * twoSumTarget内部已经去重,所以这里只需要关注第一个数字是否重复
                 * */
                while (i < n - 1 && nums[i] == nums[i + 1]) i++;
            }
            return res;
        }

        List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
            List<List<Integer>> res = new ArrayList<>();
            // 左指针从start开始，因为start前面的元素都已经被threeSumTarget计入过了
            int l = start, r = nums.length - 1;
            while (l < r) {
                int left = nums[l], right = nums[r];
                int sum = left + right;
                if (sum < target)
                    // 去重
                    while (l < r && nums[l] == left)
                        l++;
                else if (target < sum)
                    // 去重
                    while (l < r && nums[r] == right)
                        r--;
                else {
                    res.add(new ArrayList<>(List.of(left, right)));
                    // 去重
                    while (l < r && nums[l] == left) l++;
                    while (l < r && nums[r] == right) r--;
                }
            }
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        // put your test code here

    }
}