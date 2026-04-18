package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return nSumTarget(nums, 4, 0, target);
        }

        // int target 改为 long target防止整型溢出
        List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
            int sz = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (n < 2 || sz < n)
                return res;
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


    public static void main(String[] args) {
        Solution solution = new FourSum().new Solution();
        // put your test code here

    }
}