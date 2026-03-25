package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PartitionToKEqualSumSubsets {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路二：以桶视角出发，让桶去选择数字
         * 1. 通过备忘录记录每次失败时各个数字used的状态，提前剪枝
         * 2. 通过位图将used从数组优化为整型变量
         */
        public boolean canPartitionKSubsets(int[] nums, int k) {
            // 数组肯定要能分成k个部分
            if (k > nums.length) return false;
            // 数组总和肯定必须得是k的倍数
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum % k != 0) return false;
            // 理论上每个桶（集合）中的数字和
            int target = sum / k;
            // used表示各个数字是否已经被装入桶中
            int used = 0;
            // 降序排序,让大的数字先装满前面的桶,提早触发剪枝
            Arrays.sort(nums);
            for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            // 穷举，看nums是否能划分成k个和为target的子集
            return backtrack(nums, k, 0, 0, target, used);
        }

        Map<Integer, Boolean> memo = new HashMap<>();

        boolean backtrack(int[] nums, int k, int bucket, int start, int target, int used) {
            // k == 0表示所有桶都被装满了，且所有nums都被使用，因为target = sum / k
            if (k == 0) {
                return true;
            }

            // 如果当前桶装满了，则让下一个桶选择数字
            if (bucket == target)
                backtrack(nums, k - 1, 0, 0, target, used);

            // 查找备忘录
            if (memo.containsKey(used))
                return memo.get(used);

            // 遍历数字,让桶选择数字
            for (int i = start; i < nums.length; i++) {
                // 判断used第i位是否为1
                if (((used >> i) & 1) == 1)
                    continue;
                /*
                 * 剪枝：如果桶选择该数字会超出target,选择下一个数字
                 * 通过降序排序让桶先选择大的数字，可以提早触发剪枝
                 * */
                if (bucket + nums[i] > target)
                    continue;
                // index桶选择数字i
                bucket += nums[i];
                // 将used第i位设为1
                used |= 1 << i;
                // 选择下一个数字
                if (backtrack(nums, k, bucket, i + 1, target, used))
                    return true;
                // 将used第i位设为0,异或规则：1^1=0,0^1=1
                used ^= 1 << i;
                // 撤销选择
                bucket -= nums[i];
            }
            // 桶选择剩下的所有数字都装不满
            memo.put(used, false);
            return false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一：以数字视角出发，让数字选择装到哪个到桶（集合）中
         */
        public boolean canPartitionKSubsets(int[] nums, int k) {
            // 数组肯定要能分成k个部分
            if (k > nums.length) return false;
            // 数组总和肯定必须得是k的倍数
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum % k != 0) return false;
            // k个桶（集合），记录每个桶装的数字之和
            int[] bucket = new int[k];
            // 理论上每个桶（集合）中的数字和
            int target = sum / k;
            // 降序排序,让大的数字先装满前面的桶,提早触发剪枝
            Arrays.sort(nums);
            for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            // 穷举，看nums是否能划分成k个和为target的子集
            return backtrack(nums, 0, bucket, target);
        }

        boolean backtrack(int[] nums, int index, int[] bucket, int target) {
            // index==nums.length时说明数字遍历完了
            if (index == nums.length) {
                // 检查桶中的数字之和是否都为target（没有必要）
//                for (int sum : bucket)
//                    if (sum != target)
//                        return false;
                return true;
            }
            // 遍历桶,让数字选择进入哪个桶
            for (int i = 0; i < bucket.length; i++) {
                /*
                 * 剪枝：如果当前桶数字和跟之前桶数字和一样，没必要选
                 * 因为和一样，所以结果也一样
                 * 放入之前的桶不满足要求，放到当前桶也一样不满足要求，没必要选
                 * */
                if (i > 0 && bucket[i] == bucket[i - 1])
                    continue;
                /*
                 * 剪枝：如果该桶已经装满了,选择下一个桶
                 * 通过降序排序让前面的桶先装入大的数字，可以提早触发剪枝
                 * */
                if (bucket[i] + nums[index] > target)
                    continue;
                // nums[index]选择i号桶
                bucket[i] += nums[index];
                // nums[index+1]做选择,用if剪枝提前返回
                if (backtrack(nums, index + 1, bucket, target))
                    return true;
                // 撤销选择
                bucket[i] -= nums[index];
            }
            // 所有桶都选不了，返回false
            return false;
        }
    }


    public static void main(String[] args) {
        Solution solution = new PartitionToKEqualSumSubsets().new Solution();
        // put your test code here
    }
}