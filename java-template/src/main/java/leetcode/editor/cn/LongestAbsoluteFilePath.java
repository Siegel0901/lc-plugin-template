package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestAbsoluteFilePath {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：栈
         * 1. 将input以\n分割成文件或目录
         * 2. 用\t确定每个文件或目录的层级
         * 3. 目录长度入栈，文件则计算并更新最大路径长度
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param input 文件子系统字符串
         * @return 文件最大路径长度
         */
        public int lengthLongestPath(String input) {
            Deque<Integer> stk = new ArrayDeque<>();
            // 以\n分割数组
            String[] parts = input.split("\n");
            // sum记录当前路径上的目录长度总和,max记录最大路径长度
            int sum = 0, max = 0;
            for (String part : parts) {
                // 根据\t获取目录或文件层级,\t的个数即为层级，层级从0开始
                int level = part.lastIndexOf("\t") + 1;
                // 获取目录或文件长度(剔除\t)
                int partLen = part.length() - level;
                /*
                 * dir level=0 size=0 push size=1
                 * 	subdir1 level=1 size=1 push size=2
                 * 		file1.ext level=2 size=2 update max
                 * 		subsubdir1 level=2 size=2 push size=3
                 * 	subdir2 level=1 size=3 pop pop size=1 push size=2
                 * 		subsubdir2 level=2 size=2 push size=3
                 * 			file2.ext level=3 size=3 update max
                 * level和size的关系：size表示下一个层级，level表示当前层级
                 * 如果元素要入栈，则level需要跟size对齐
                 */
                while (level < stk.size())
                    sum -= stk.pop();
                // 如果是文件，则更新最大路径长度
                if (part.contains(".")) {
                    // 需要算上"/"的数量，即level
                    max = Math.max(max, sum + partLen + level);
                } else {
                    // 如果是目录，则加入栈中，更新长度总和
                    stk.push(partLen);
                    sum += partLen;
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestAbsoluteFilePath().new Solution();
        // put your test code here
        /*
         * dir 0
         * 	subdir1 1
         * 		file1.ext 2
         * 		subsubdir1 2
         * 	subdir2 1
         * 		subsubdir2 2
         * 			file2.ext 3
         */
//        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//        String[] split = input.split("\n");
//        for (String s : split) {
//            System.out.print(s + " ");
//            System.out.println(s.lastIndexOf("\t") + 1);
//        }
        System.out.println(solution.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }
}