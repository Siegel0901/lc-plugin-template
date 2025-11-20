package leetcode.editor.cn;

import java.util.HashSet;

public class ContainsDuplicate {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            HashSet<Integer> hashSet = new HashSet<>();
            for (int num : nums) {
                if (hashSet.contains(num))
                    return true;
                hashSet.add(num);
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ContainsDuplicate().new Solution();
        // put your test code here

    }
}