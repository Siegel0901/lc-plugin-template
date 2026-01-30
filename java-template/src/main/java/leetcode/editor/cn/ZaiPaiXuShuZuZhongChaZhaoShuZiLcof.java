package leetcode.editor.cn;

public class ZaiPaiXuShuZuZhongChaZhaoShuZiLcof {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：二分查找上下界，若不为[-1,-1]则返回right-left+1
         * 时间复杂度：O(logn)
         * 空间复杂度：O(1)
         *
         * @param scores 成绩数组
         * @param target 目标成绩
         * @return 出现次数
         */
        public int countTarget(int[] scores, int target) {
            int left = left_bound(scores, target);
            int right = right_bound(scores, target);
            if (left == -1 && right == -1)
                return 0;
            else
                return right - left + 1;
        }

        public int left_bound(int[] scores, int target) {
            int left = 0, right = scores.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (scores[mid] < target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            if (left < 0 || left >= scores.length)
                return -1;
            return scores[left] == target ? left : -1;
        }

        public int right_bound(int[] scores, int target) {
            int left = 0, right = scores.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (scores[mid] <= target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            if (right < 0 || right >= scores.length)
                return -1;
            return scores[right] == target ? right : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ZaiPaiXuShuZuZhongChaZhaoShuZiLcof().new Solution();
        // put your test code here

    }
}