package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shift2dGrid {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：转换为一维数组，整体翻转后，再以k分区翻转
         * 1. 判断k对元素个数取余是否为0，若为0，则直接返回grid
         * 2. 遍历数组，将元素放到一维数组中
         * 3. 翻转一维数组
         * 4. 翻转[0, k - 1]和[k, m * n - 1]
         * 5. 将一维数组存回gird
         * 时间复杂度：O(m * n)
         * 空间复杂度：O(m * n)
         *
         * @param grid 矩阵
         * @param k    移动的步数
         * @return 移动后的矩阵
         */
//        public List<List<Integer>> shiftGrid(int[][] grid, int k) {
//            int m = grid.length;
//            int n = grid[0].length;
//            List<List<Integer>> res = new ArrayList<>();
//            k %= m * n;
//            int[] nums = Arrays.stream(grid)        // 处理grid的行
//                    .flatMapToInt(Arrays::stream)   // 每行转换为IntStream
//                    .toArray();                     // 合并成int数组
//            reverse(nums, 0, nums.length - 1);
//            reverse(nums, 0, k - 1);
//            reverse(nums, k, nums.length - 1);
//            res = IntStream.range(0, m)                             // ① 生成行索引 0,1,..,m - 1
//                    .mapToObj(i ->                                  // ② 对每个行号 i，构造一行 List<Integer>
//                            IntStream.range(0, n)                   // ③ 生成列索引 0,1,...,n - 1
//                                    .map(j -> nums[i * n + j])      // ④ 计算一维数组中的位置并取值
//                                    .boxed()                        // ⑤ 将 int 转为 Integer
//                                    .collect(Collectors.toList()))  // ⑥ 收集为 List<Integer>
//                    .collect(Collectors.toList());                  // ⑦ 收集所有行，得到 List<List<Integer>>
//            return res;
//        }
//
//        public void reverse(int[] nums, int start, int end) {
//            while (start < end) {
//                int temp = nums[start];
//                nums[start++] = nums[end];
//                nums[end--] = temp;
//            }
//        }
//    }

        /**
         * 思路二：原地修改grid
         * 1. 自定义set和get方法用一维数组索引修改和访问二维数组
         * 时间复杂度：O(m * n)
         * 空间复杂度：O(1)
         *
         * @param grid 矩阵
         * @param k    移动的步数
         * @return 移动后的矩阵
         */
        public List<List<Integer>> shiftGrid(int[][] grid, int k) {
            int m = grid.length;
            int n = grid[0].length;
            List<List<Integer>> res = new ArrayList<>();
            k %= m * n;
            reverse(grid, 0, m * n - 1);
            reverse(grid, 0, k - 1);
            reverse(grid, k, m * n - 1);
            res = Arrays.stream(grid)                       // 处理grid的行
                    .map(row -> Arrays.stream(row)          // 对每行，构造一行 List<Integer>
                            .boxed()                        // 将 int 转为 Integer
                            .collect(Collectors.toList()))  // 收集为 List<Integer>
                    .collect(Collectors.toList());          // 收集所有行，得到 List<List<Integer>>
            return res;
        }

        public void set(int[][] grid, int index, int val) {
            int n = grid[0].length;
            grid[index / n][index % n] = val;
        }

        public int get(int[][] grid, int index) {
            int n = grid[0].length;
            return grid[index / n][index % n];
        }

        public void reverse(int[][] grid, int start, int end) {
            while (start < end) {
                int temp = get(grid, start);
                set(grid, start++, get(grid, end));
                set(grid, end--, temp);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new Shift2dGrid().new Solution();
        // put your test code here
        solution.shiftGrid(new int[][]{
                {3, 8, 1, 9},
                {19, 7, 2, 5},
                {4, 6, 11, 10},
                {12, 0, 21, 13}
        }, 4);
    }
}