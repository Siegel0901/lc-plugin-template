package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOranges {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class State {
            int i;
            int j;

            public State(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


        public int orangesRotting(int[][] grid) {
            Deque<State> dq = new ArrayDeque<>();
            int step = 0;
            int m = grid.length, n = grid[0].length;
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 || grid[i][j] == 2) {
                        count++;
                        if (grid[i][j] == 2)
                            dq.offer(new State(i, j));
                    }
                }
            }
            if (dq.size() == count)
                return 0;
            int dqSize = 0;
            while (!dq.isEmpty()) {
                int sz = dq.size();
                dqSize += sz;
                for (int i = 0; i < sz; i++) {
                    State cur = dq.poll();
                    for (int[] dir : dirs) {
                        int next_i = cur.i + dir[0];
                        int next_j = cur.j + dir[1];
                        if (next_i < 0 || next_j < 0 || next_i >= m || next_j >= n)
                            continue;
                        if (grid[next_i][next_j] == 0 || grid[next_i][next_j] == 2)
                            continue;
                        grid[next_i][next_j] = 2;
                        dq.offer(new State(next_i, next_j));
                    }
                }
                step++;
            }
            if (dqSize == count)
                return step - 1;
            else
                return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RottingOranges().new Solution();
        // put your test code here

    }
}