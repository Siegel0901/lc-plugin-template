package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class SlidingPuzzle {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int slidingPuzzle(int[][] board) {
            int m = 2, n = 3;
            // 目标状态
            String target = "123450";
            // 起始状态
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    sb.append(board[i][j]);
            String start = sb.toString();

            /*
             * 将二维数组转为一维数组
             * 同时记录二维数组中每个元素的邻居在一维数组中的索引
             * 1 2 3    0 1 2 3 4 5
             *       ->
             * 4 5 0    1 2 3 4 5 0
             * */
            int[][] neigh = new int[][]{
                    {1, 3},
                    {0, 2, 4},
                    {1, 5},
                    {0, 4},
                    {3, 1, 5},
                    {2, 4},
            };

            Deque<String> dq = new ArrayDeque<>();
            // visited用来记录状态是否到达过，防止走回头路
            Set<String> visited = new HashSet<>();

            // 起始状态入队并记录
            dq.offer(start);
            visited.add(start);

            // 初始步数为0
            int step = 0;
            while (!dq.isEmpty()) {
                // sz代表当前步数的状态数
                int sz = dq.size();
                for (int i = 0; i < sz; i++) {
                    String cur = dq.poll();
                    // 找到目标状态，返回步数
                    if (target.equals(cur))
                        return step;
                    // 找到0在当前状态中的索引
                    int idx = 0;
                    for (; cur.charAt(idx) != '0'; idx++) ;
                    // 找到该索引的邻居
                    for (int adj : neigh[idx]) {
                        // 记录0与每个邻居交换位置后的新状态
                        String new_board = swap(cur.toCharArray(), idx, adj);
                        // 若该状态未达到过，则加入队列并记录
                        if (!visited.contains(new_board)) {
                            dq.offer(new_board);
                            visited.add(new_board);
                        }
                    }
                }
                // 当前状态下，0与邻居交换的新状态都已加入队列，该走下一步了
                step++;
            }

            // 找不到目标状态
            return -1;
        }


        String swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            return new String(chars);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SlidingPuzzle().new Solution();
        // put your test code here

    }
}