package leetcode.editor.cn;

import java.util.*;

public class MinimumCostToMakeAtLeastOneValidPathInAGrid {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 使用0-1BFS优化时间复杂度
     * 0-1BFS是一种特殊的BFS，适用于边权为0或1的最短路径问题
     * 时间复杂度：O(MN)，避免了优先队列的O(logMN)堆处理开销
     */
    class Solution {
        public int minCost(int[][] grid) {
            return dijkstra(grid);
        }

        class State {
            int x;
            int y;
            int distFromSrc;

            public State(int x, int y, int distFromSrc) {
                this.x = x;
                this.y = y;
                this.distFromSrc = distFromSrc;
            }
        }

        int dijkstra(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] distTo = new int[m][n];
            for (int[] dist : distTo)
                Arrays.fill(dist, Integer.MAX_VALUE);
            Deque<State> d = new ArrayDeque<>();
            distTo[0][0] = 0;
            d.offer(new State(0, 0, 0));
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            while (!d.isEmpty()) {
                // 队首元素是当前距离最小的节点
                State minDistNode = d.pollFirst();
                int curX = minDistNode.x;
                int curY = minDistNode.y;
                int curDistFromSrc = minDistNode.distFromSrc;
                if (curDistFromSrc == distTo[curX][curY]) {
                    if (curX == m - 1 && curY == n - 1)
                        return distTo[curX][curY];
                    int noCost = grid[curX][curY] - 1;
                    for (int dir = 0; dir < dirs.length; dir++) {
                        int neighborX = curX + dirs[dir][0];
                        int neighborY = curY + dirs[dir][1];
                        if (neighborX < 0 || neighborX >= m || neighborY < 0 || neighborY >= n)
                            continue;
                        int neighborFromSrc = curDistFromSrc + 1;
                        if (dir == noCost)
                            neighborFromSrc = curDistFromSrc;
                        if (neighborFromSrc < distTo[neighborX][neighborY]) {
                            distTo[neighborX][neighborY] = neighborFromSrc;
                            // 边权为 0 加入队首，边权为 1 加入队尾
                            if (dir == noCost)
                                d.offerFirst(new State(neighborX, neighborY, neighborFromSrc));
                            else
                                d.offerLast(new State(neighborX, neighborY, neighborFromSrc));
                        }
                    }
                }
            }
            return distTo[m - 1][n - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 不建图，直接使用grid数组
     */
    class Solution2 {
        public int minCost(int[][] grid) {
            return dijkstra(grid);
        }

        class State {
            int x;
            int y;
            int distFromSrc;

            public State(int x, int y, int distFromSrc) {
                this.x = x;
                this.y = y;
                this.distFromSrc = distFromSrc;
            }
        }

        int dijkstra(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] distTo = new int[m][n];
            for (int[] dist : distTo)
                Arrays.fill(dist, Integer.MAX_VALUE);
            Queue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distFromSrc));
            distTo[0][0] = 0;
            pq.offer(new State(0, 0, 0));
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            while (!pq.isEmpty()) {
                State minDistNode = pq.poll();
                int curX = minDistNode.x;
                int curY = minDistNode.y;
                int curDistFromSrc = minDistNode.distFromSrc;
                if (curDistFromSrc == distTo[curX][curY]) {
                    if (curX == m - 1 && curY == n - 1)
                        return distTo[curX][curY];
                    int noCost = grid[curX][curY] - 1;
                    for (int dir = 0; dir < dirs.length; dir++) {
                        int neighborX = curX + dirs[dir][0];
                        int neighborY = curY + dirs[dir][1];
                        if (neighborX < 0 || neighborX >= m || neighborY < 0 || neighborY >= n)
                            continue;
                        int neighborFromSrc = curDistFromSrc + 1;
                        if (dir == noCost)
                            neighborFromSrc = curDistFromSrc;
                        if (neighborFromSrc < distTo[neighborX][neighborY]) {
                            distTo[neighborX][neighborY] = neighborFromSrc;
                            pq.offer(new State(neighborX, neighborY, neighborFromSrc));
                        }
                    }
                }
            }
            return distTo[m - 1][n - 1];
        }
    }

    /**
     * 1. 时间复杂度：O(MNlogMN)
     * 1.1. 建图：O(MN)
     * 初始化 M×N 个空列表：O(M × N)
     * 遍历每个格子 (i, j)：O(M × N)
     * 对每个格子，检查 4 个方向（常数）：O(1)
     * 总计：O(M × N)
     * 1.2. Dijkstra算法：O(MNlogMN)
     * 节点总数 V = M × N（每个格子是一个节点）
     * 边数 E = 4 × M × N（每个格子最多 4 条出边）
     * 每个节点最多被处理一次：O(V) = O(M × N)
     * 每条边最多被松弛一次：O(E) = O(M × N)
     * 每次优先队列操作（offer/poll）：O(log V) = O(log(M × N))
     * 最坏情况下，每条边都可能触发一次入队操作
     * 总计：O(E × log V) = O(M × N × log(M × N))
     * 2. 空间复杂度：O(MN)
     * 2.1. 图的存储（邻接表）：O(M × N)
     * 二维数组 graph：O(M × N)
     * 每个格子最多存储 4 条边（常数）：O(1)
     * 所有边的总存储：O(M × N)
     * 2.2. 距离数组 distTo：O(M × N)
     * 二维数组，存储从起点到每个格子的最小代价
     * 2.3. 优先队列 pq：O(M × N)
     * 最坏情况下，队列中可能有 M × N 个 State 对象
     * State 对象：每个 State 占用 O(1) 空间，最多 M × N 个同时存在于队列中
     * 总体空间复杂度：O(M × N + M × N + M × N) = O(M × N)
     */
    class Solution1 {
        /**
         * 本质上仍是在求从起点到终点的最短路径
         * 只不过路径上的边权比较特殊
         * 如果到达邻居节点的方向是grid[i][j]指向的方向，则边权为0，否则为1（需要修改）
         */
        public int minCost(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            List<int[]>[][] graph = buildGraph(grid, m, n);
            return dijkstra(graph, m - 1, n - 1);
        }

        List<int[]>[][] buildGraph(int[][] grid, int m, int n) {
            List<int[]>[][] graph = new ArrayList[m][n];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    graph[i][j] = new ArrayList<>();
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int noCost = grid[i][j] - 1;
                    for (int dir = 0; dir < dirs.length; dir++) {
                        int x = dirs[dir][0];
                        int y = dirs[dir][1];
                        int neighborX = i + x;
                        int neighborY = j + y;
                        // 先判断邻居节点是否越界
                        if (neighborX < 0 || neighborX >= m || neighborY < 0 || neighborY >= n)
                            continue;
                        // 如果到达邻居节点的方向是grid[i][j]指向的方向，则边权为0，否则为1（需要修改）
                        if (dir == noCost)
                            graph[i][j].add(new int[]{neighborX, neighborY, 0});
                        else
                            graph[i][j].add(new int[]{neighborX, neighborY, 1});
                    }
                }
            }
            return graph;
        }

        class State {
            int x;
            int y;
            int distFromSrc;

            public State(int x, int y, int distFromSrc) {
                this.x = x;
                this.y = y;
                this.distFromSrc = distFromSrc;
            }
        }

        int dijkstra(List<int[]>[][] graph, int destX, int destY) {
            int[][] distTo = new int[destX + 1][destY + 1];
            for (int[] dist : distTo)
                Arrays.fill(dist, Integer.MAX_VALUE);
            Queue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distFromSrc));
            distTo[0][0] = 0;
            pq.offer(new State(0, 0, 0));
            while (!pq.isEmpty()) {
                State minDistNode = pq.poll();
                int curX = minDistNode.x;
                int curY = minDistNode.y;
                int curDistFromSrc = minDistNode.distFromSrc;
                if (curDistFromSrc == distTo[curX][curY]) {
                    if (curX == destX && curY == destY)
                        return distTo[curX][curY];
                    for (int[] edge : graph[curX][curY]) {
                        int neighborX = edge[0];
                        int neighborY = edge[1];
                        int neighborFromSrc = curDistFromSrc + edge[2];
                        if (neighborFromSrc < distTo[neighborX][neighborY]) {
                            distTo[neighborX][neighborY] = neighborFromSrc;
                            pq.offer(new State(neighborX, neighborY, neighborFromSrc));
                        }
                    }
                }
            }
            return distTo[destX][destY];
        }
    }


    public static void main(String[] args) {
        Solution solution = new MinimumCostToMakeAtLeastOneValidPathInAGrid().new Solution();
        // put your test code here

    }
}