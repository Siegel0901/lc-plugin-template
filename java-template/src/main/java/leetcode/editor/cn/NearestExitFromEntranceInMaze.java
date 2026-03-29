package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class NearestExitFromEntranceInMaze {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class State {
            int x;
            int y;

            public State(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int nearestExit(char[][] maze, int[] entrance) {
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            int m = maze.length, n = maze[0].length;
            boolean[][] visited = new boolean[m][n];
            Deque<State> dq = new ArrayDeque<>();
            int start_x = entrance[0], start_y = entrance[1];
            dq.offer(new State(start_x, start_y));
            visited[start_x][start_y] = true;
            int step = 0;
            while (!dq.isEmpty()) {
                int sz = dq.size();
                for (int i = 0; i < sz; i++) {
                    State cur = dq.poll();
                    int cur_x = cur.x;
                    int cur_y = cur.y;
                    if ((cur_x != start_x || cur_y != start_y)
                            && maze[cur_x][cur_y] == '.'
                            && (cur_x == 0 || cur_y == 0 || cur_x == m - 1 || cur_y == n - 1))
                        return step;
                    for (int[] dir : dirs) {
                        int next_x = cur_x + dir[0];
                        int next_y = cur_y + dir[1];
                        if (next_x < 0 || next_y < 0 || next_x >= m || next_y >= n)
                            continue;
                        if (maze[next_x][next_y] == '+')
                            visited[next_x][next_y] = true;
                        if (visited[next_x][next_y])
                            continue;
                        dq.offer(new State(next_x, next_y));
                        visited[next_x][next_y] = true;
                    }
                }
                step++;
            }
            return -1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NearestExitFromEntranceInMaze().new Solution();
        // put your test code here

    }
}