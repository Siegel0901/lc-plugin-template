package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleAnArray {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：洗牌算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    class Solution {
        int[] original;
        int[] shuffled;
        Random rd;

        public Solution(int[] nums) {
            original = nums.clone();
            shuffled = nums.clone();
            rd = new Random();
        }

        public int[] reset() {
            shuffled = original.clone();
            return shuffled;
        }

        // 洗牌算法
        public int[] shuffle() {
            for (int i = shuffled.length - 1; i >= 0; i--) {
                // 每个元素的随机范围为[0,i+1),即[0,i]，可以保证n!种排列
                int j = rd.nextInt(i + 1);
                swap(shuffled, i, j);
            }
            return shuffled;
        }

        void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int[] param_1 = obj.reset();
     * int[] param_2 = obj.shuffle();
     */
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：回溯构造全排列+随机获取全排列
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    class Solution1 {
        int[] nums;
        List<int[]> shuffled;
        List<Integer> track;
        boolean[] visited;

        public Solution1(int[] nums) {
            this.nums = nums;
            shuffled = new ArrayList<>();
            track = new ArrayList<>();
            visited = new boolean[nums.length];
            backtrack(nums, 0);
        }

        public int[] reset() {
            return nums;
        }

        public int[] shuffle() {
            // 取[0,shuffled.size()-1]的随机数
            int idx = new Random().nextInt(shuffled.size());
            return shuffled.get(idx);
        }

        void backtrack(int[] nums, int idx) {
            if (idx == nums.length) {
                shuffled.add(track.stream().mapToInt(i -> i).toArray());
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i])
                    continue;
                visited[i] = true;
                track.add(nums[i]);
                backtrack(nums, idx + 1);
                visited[i] = false;
                track.remove(track.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
//        Solution solution = new ShuffleAnArray().new Solution();
        // put your test code here

    }
}