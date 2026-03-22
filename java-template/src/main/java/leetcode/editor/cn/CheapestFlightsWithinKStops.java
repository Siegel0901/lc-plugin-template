package leetcode.editor.cn;

import java.util.*;

public class CheapestFlightsWithinKStops {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 1. 时间复杂度：O(K × E × log(K × V))
     * 其中：
     * V = 节点数量（n）
     * E = 边的数量（flights.length）
     * K = 最多允许的边数（题目中的 k+1）
     * 1.1. 状态空间大小
     * 每个节点可以有 k+1 种状态（经过 0, 1, 2, ..., k 条边）
     * 总状态数 = V × (K+1) = O(K × V)
     * 1.2. 优先队列操作次数
     * 1.2.1. 在最坏情况下：
     * 每个状态都可能被加入优先队列一次
     * 每条边都可能被松弛一次
     * 总入队次数 = O(K × E)
     * 1.2.2. 每次优先队列操作的时间复杂度：O(log(队列大小))
     * 队列中最多有 O(K × V) 个状态
     * 单次操作：O(log(K × V))
     * 1.3. 总时间复杂度
     * 总时间 = 入队次数 * 单次操作 = O(K × E) * O(log(K × V)) = O(K × E × log(K × V))
     * 2. 空间复杂度：O(K × V + E)
     * 2.1. 图的存储空间
     * 邻接表：O(V + E)，通常 E ≥ V，简化为 O(E)
     * 2.2. distTo 数组：O(K × V)
     * 第一维：V 个节点
     * 第二维：每个节点最多 K+1 种状态
     * 2.3. 优先队列：O(K × V)
     * 最坏情况下，队列中有 O(K × V) 个 State 对象
     * 每个 State 占用 O(1) 空间（3 个 int 字段）
     * 2.4. 总空间复杂度
     * 总空间 = 图 + 距离数组 + 优先队列
     *        = O(E) + O(K × V) + O(K × V)
     *        = O(K × V + E)
     */
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            List<int[]>[] graph = buildGraph(n, flights);
            // 最多经过k个节点，即最多经过k+1个边
            return dijkstra(graph, src, dst, k + 1);
        }

        List<int[]>[] buildGraph(int n, int[][] flights) {
            List<int[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int[] f : flights) {
                int from = f[0];
                int to = f[1];
                int price = f[2];
                graph[from].add(new int[]{to, price});
            }
            return graph;
        }

        class State {
            int node;
            int distFromSrc;
            // 从起点到当前节点的路径经过的边数
            int edgesFromSrc;

            public State(int node, int distFromSrc, int edgesFromSrc) {
                this.node = node;
                this.distFromSrc = distFromSrc;
                this.edgesFromSrc = edgesFromSrc;
            }
        }

        int dijkstra(List<int[]>[] graph, int src, int dst, int x) {
            // distTo[i][j] 的含义：从 src 到达节点 i 恰好经过 j 条边的最短路径
            int[][] distTo = new int[graph.length][x + 1];
            for (int[] dist : distTo)
                Arrays.fill(dist, Integer.MAX_VALUE);
            Queue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distFromSrc));
            distTo[src][0] = 0;
            pq.offer(new State(src, 0, 0));
            while (!pq.isEmpty()) {
                State minDistNode = pq.poll();
                int cur = minDistNode.node;
                int curDist = minDistNode.distFromSrc;
                int curEdges = minDistNode.edgesFromSrc;
                if (curDist == distTo[cur][curEdges]) {
                    if (cur == dst)
                        return distTo[cur][curEdges];
                    for (int[] edge : graph[cur]) {
                        int nb = edge[0];
                        int nbDist = curDist + edge[1];
                        int nbEdges = curEdges + 1;
                        /*
                        * 必须要先判断 nbEdges <= x，因为distTo第二维的大小是 x + 1，范围是 [0,x]
                        * 如果 nbEdges > x，先判断 nbDist < distTo[nb][nbEdges] 会数组越界
                        * */
                        if (nbEdges <= x && nbDist < distTo[nb][nbEdges]) {
                            distTo[nb][nbEdges] = nbDist;
                            pq.offer(new State(nb, nbDist, nbEdges));
                        }
                    }
                }
            }
            return -1;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new CheapestFlightsWithinKStops().new Solution();
        // put your test code here

    }
}