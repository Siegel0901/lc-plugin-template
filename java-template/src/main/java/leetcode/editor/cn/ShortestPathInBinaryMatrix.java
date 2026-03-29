package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ShortestPathInBinaryMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 进记录坐标到起点的距离进行BFS
     */
    class Solution {
        class State {
            int i;
            int j;

            public State(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        int n;

        public int shortestPathBinaryMatrix(int[][] grid) {
            n = grid.length;
            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
                return -1;
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            Deque<State> dq = new ArrayDeque<>();
            dq.offer(new State(0, 0));
            grid[0][0] = 1;
            int step = 0;
            while (!dq.isEmpty()) {
                int sz = dq.size();
                for (int i = 0; i < sz; i++) {
                    State cur = dq.poll();
                    int cur_i = cur.i;
                    int cur_j = cur.j;
                    if (cur_i == n - 1 && cur_j == n - 1)
                        return step + 1;
                    for (int[] dir : dirs) {
                        int next_i = cur_i + dir[0];
                        int next_j = cur_j + dir[1];
                        if (next_i < 0 || next_j < 0 || next_i >= n || next_j >= n)
                            continue;
                        if (grid[next_i][next_j] == 1)
                            continue;
                        dq.offer(new State(next_i, next_j));
                        grid[next_i][next_j] = 1;
                    }
                }
                step++;
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 将整个网格作为状态进行BFS
     * 【Time Limit Exceeded】
     */
    class Solution1 {
        class State {
            int i;
            int j;
            String state;

            public State(int i, int j, String state) {
                this.i = i;
                this.j = j;
                this.state = state;
            }
        }

        int n;

        public int shortestPathBinaryMatrix(int[][] grid) {
            n = grid.length;
            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
                return -1;
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            Set<String> visited = new HashSet<>();
            Deque<State> dq = new ArrayDeque<>();
            grid[0][0] = 1;
            String start = gridToString(grid);
            dq.offer(new State(0, 0, start));
            visited.add(start);
            int step = 0;
            while (!dq.isEmpty()) {
                int sz = dq.size();
                for (int i = 0; i < sz; i++) {
                    State cur = dq.poll();
                    int cur_i = cur.i;
                    int cur_j = cur.j;
                    if (cur_i == n - 1 && cur_j == n - 1)
                        return step + 1;
                    for (int[] dir : dirs) {
                        int next_i = cur_i + dir[0];
                        int next_j = cur_j + dir[1];
                        int[][] state = stringToGrid(cur.state);
                        if (next_i < 0 || next_j < 0 || next_i >= n || next_j >= n)
                            continue;
                        if (state[next_i][next_j] == 1)
                            continue;
                        state[next_i][next_j] = 1;
                        String nextState = gridToString(state);
                        if (visited.contains(nextState))
                            continue;
                        dq.offer(new State(next_i, next_j, nextState));
                        visited.add(nextState);
                    }
                }
                step++;
            }

            return -1;
        }

        String gridToString(int[][] grid) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    sb.append((char) (grid[i][j] + '0'));
            return sb.toString();
        }

        int[][] stringToGrid(String s) {
            char[] chs = s.toCharArray();
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    grid[i][j] = chs[i * n + j] - '0';
            return grid;
        }
    }


    public static void main(String[] args) {
        Solution solution = new ShortestPathInBinaryMatrix().new Solution();
        // put your test code here
        int res = solution.shortestPathBinaryMatrix(new int[][]{
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 0},
        });
        System.out.println(res);
    }
}