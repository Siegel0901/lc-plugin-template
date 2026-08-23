package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllNumbersDisappearedInAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：原地哈希
     * 1. 用数组自身当哈希表，数组长度为n，值域为[1,n]，正好可以对应下标[0,n-1]。
     * 2. 利用正负号作为标记，遍历数组，对于每个值num，把下标|num|-1处的元素标记为负数。
     * 2.1. 负数表示“这个下标对应的数字出现过”。
     * 3. 第二次遍历时，哪个下标还是正数，就说明对应的数字没有出现过。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                // 获得nums[i]对应的下标，取绝对值是因为数字i出现过，nums[i]可能已经被标记为负数
                int idx = Math.abs(nums[i]) - 1;
                // 如果该下标处的元素是正数，则取负，负数则跳过
                if (nums[idx] > 0)
                    nums[idx] = -nums[idx];
            }
            List<Integer> res = new ArrayList<>();
            // 再次遍历数组，若数组下标i对应的元素为正，则该i+1未在数组中出现过
            for (int i = 0; i < nums.length; i++)
                if (nums[i] > 0)
                    res.add(i + 1);
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：哈希集合
     * 1. 遍历数组，加入哈希集合
     * 2. 遍历1~n，判断集合中是否存在
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums)
                set.add(num);
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i <= nums.length; i++)
                if (!set.contains(i))
                    res.add(i);
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new FindAllNumbersDisappearedInAnArray().new Solution();
        // put your test code here

    }
}