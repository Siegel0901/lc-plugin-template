package leetcode.editor.cn;

import java.util.*;

public class PathWithMinimumEffort {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：
         * 1. 不必浪费空间构建完成的图，直接根据heights获取每个节点的邻居及其权重构成当个节点的邻接表
         * 2. State中可以用两个变量定位二维数组中的节点
         * 3. dijkstra的effortTo[][]数组表示源点到(i,j)的最小体力消耗（最小的路径最大高度差）
         * 4. 点对点最短路径可以在遇到终点时，若最短路径已经确定，直接返回
         * 5. 本题的要求不是路径权重和最小,而是路径上的最大边权重要在各个路径中最小
         */
        public int minimumEffortPath(int[][] heights) {
            return dijkstra(heights);
        }

        // 返回(x,y)的上下左右相邻坐标及其体力消耗值
        List<int[]> getEdges(int[][] heights, int x, int y) {
            int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int row = heights.length;
            int col = heights[0].length;
            List<int[]> neighbors = new ArrayList<>();
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= row || nx < 0 || ny >= col || ny < 0)
                    continue;
                neighbors.add(new int[]{nx, ny, Math.abs(heights[nx][ny] - heights[x][y])});
            }
            return neighbors;
        }


        class State {
            int row;
            int col;
            // 表示(row,col)到源点的体力消耗值
            int effortFromSrc;

            public State(int row, int col, int effortFromSrc) {
                this.row = row;
                this.col = col;
                this.effortFromSrc = effortFromSrc;
            }
        }

        int dijkstra(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            int[][] effortTo = new int[row][col];
            for (int[] effortToRow : effortTo)
                Arrays.fill(effortToRow, Integer.MAX_VALUE);
            Queue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.effortFromSrc));
            effortTo[0][0] = 0;
            pq.offer(new State(0, 0, 0));
            while (!pq.isEmpty()) {
                State minEffortNode = pq.poll();
                int curX = minEffortNode.row;
                int curY = minEffortNode.col;
                int curEffortFromSrc = minEffortNode.effortFromSrc;
                if (curEffortFromSrc == effortTo[curX][curY]) {
                    // 终点的状态已经确定,直接返回
                    if (curX == row - 1 && curY == col - 1)
                        return effortTo[curX][curY];
                    for (int[] edge : getEdges(heights, curX, curY)) {
                        int neighborX = edge[0];
                        int neighborY = edge[1];
                        // 计算源点到邻居的体力消耗值
                        int neighborEffortFromSrc = Math.max(curEffortFromSrc, edge[2]);
                        if (neighborEffortFromSrc < effortTo[neighborX][neighborY]) {
                            effortTo[neighborX][neighborY] = neighborEffortFromSrc;
                            pq.offer(new State(neighborX, neighborY, neighborEffortFromSrc));
                        }
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PathWithMinimumEffort().new Solution();
        // put your test code here
        System.out.println(solution.minimumEffortPath(new int[][]{
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5},
        }));
    }
}