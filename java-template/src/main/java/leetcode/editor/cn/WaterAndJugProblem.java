package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WaterAndJugProblem {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class State {
            int x;
            int y;

            State(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public boolean canMeasureWater(int x, int y, int target) {
            if (x + y < target)
                return false;
            Deque<State> dq = new ArrayDeque<>();
            boolean[][] visited = new boolean[x + 1][y + 1];
            dq.offer(new State(0, 0));
            visited[0][0] = true;
            while (!dq.isEmpty()) {
                State cur = dq.poll();
                if (cur.x == target || cur.y == target || cur.x + cur.y == target)
                    return true;
                List<State> neighs = new ArrayList<>();
                neighs.add(fillX(cur, x));
                neighs.add(fillY(cur, y));
                neighs.add(clearX(cur));
                neighs.add(clearY(cur));
                neighs.add(xToY(cur, y));
                neighs.add(yToX(cur, x));
                for (State neigh : neighs) {
                    if (visited[neigh.x][neigh.y])
                        continue;
                    dq.offer(neigh);
                    visited[neigh.x][neigh.y] = true;
                }
            }
            return false;
        }

        State fillX(State state, int x) {
            return new State(x, state.y);
        }

        State fillY(State state, int y) {
            return new State(state.x, y);
        }

        State clearX(State state) {
            return new State(0, state.y);
        }

        State clearY(State state) {
            return new State(state.x, 0);
        }

        State xToY(State state, int limitY) {
            if (state.x == 0 && state.y == 0)
                return new State(0, 0);
            if (state.x == 0)
                return new State(state.x, state.y);
            if (state.y == 0) {
                if (state.x >= limitY)
                    return new State(state.x - limitY, limitY);
                else
                    return new State(0, state.x);
            }
            int diff = limitY - state.y;
            if (state.x >= diff)
                return new State(state.x - diff, limitY);
            else
                return new State(0, state.y + state.x);
        }

        State yToX(State state, int limitX) {
            State s = xToY(new State(state.y, state.x), limitX);
            return new State(s.y, s.x);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new WaterAndJugProblem().new Solution();
        // put your test code here
        solution.canMeasureWater(3, 5, 4);
    }
}