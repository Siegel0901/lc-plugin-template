package leetcode.editor.cn;

import java.util.*;

public class PathWithMaximumProbability {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 时空复杂度分析
     * 1. 时间复杂度：O(E × log V)
     * 1.1. 建图过程（buildGraph 方法）：O(E)
     * 初始化 n 个空列表：O(V)
     * 遍历所有边并添加到邻接表：O(E)
     * 总计：O(V + E)，通常 E ≥ V，所以简化为 O(E)
     * 1.2. Dijkstra 主循环（dijkstra 方法）：O(E × log V)
     * 每个节点最多被处理一次：O(V)
     * 每条边最多被松弛一次：O(E)
     * 每次优先队列操作（offer/poll）：O(log V)
     * 最坏情况下，每条边都可能触发一次入队操作
     * 总计：O(E × log V)
     * 1.3. 总体时间复杂度：O(E + E × log V) = O(E × log V)
     * 2. 空间复杂度：O(V + E)
     * 2.1. 图的存储（邻接表）：O(V + E)
     * 数组 graph：O(V)
     * 所有边的存储：O(E)
     * 2.2. 距离数组 distTo：O(V)
     * 存储从起点到每个节点的最大概率
     * 2.3. 优先队列 pq：O(V)
     * 最坏情况下，队列中可能有 V 个 State 对象
     * State 对象：每个 State 占用 O(1) 空间，最多 V 个同时存在于队列中
     * 2.4. 总体空间复杂度：O(V + E + V + V) = O(V + E)
     */
    class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
            List<double[]>[] graph = buildGraph(n, edges, succProb);
            return dijkstra(graph, start_node, end_node);
        }

        List<double[]>[] buildGraph(int n, int[][] edges, double[] succProb) {
            List<double[]>[] graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int i = 0; i < edges.length; i++) {
                int from = edges[i][0], to = edges[i][1];
                // 无向图
                graph[from].add(new double[]{to, succProb[i]});
                graph[to].add(new double[]{from, succProb[i]});
            }
            return graph;
        }

        class State {
            int node;
            double distFromSrc;

            public State(int node, double distFromSrc) {
                this.node = node;
                this.distFromSrc = distFromSrc;
            }

            public double getDistFromSrc() {
                return distFromSrc;
            }
        }

        /**
         * 需要注意的点：
         * 1. 边权概率类型使用double
         * 2. 概率越乘越小，需要使用最大堆，每次选择概率最大的边
         * 3. distTo数组初值为Double.MIN_VALUE，起点的初始概率为1，不能为0
         */
        double dijkstra(List<double[]>[] graph, int start, int end) {
            double[] distTo = new double[graph.length];
            Arrays.fill(distTo, Double.MIN_VALUE);
            Queue<State> pq = new PriorityQueue<>(Comparator.comparingDouble(State::getDistFromSrc).reversed());
            distTo[start] = 1;
            pq.offer(new State(start, 1));
            while (!pq.isEmpty()) {
                State minDistNode = pq.poll();
                int curNode = minDistNode.node;
                double curDistFromSrc = minDistNode.distFromSrc;
                if (curDistFromSrc == distTo[curNode]) {
                    if (curNode == end)
                        return distTo[end];
                    for (double[] edge : graph[curNode]) {
                        int neighborNode = (int) edge[0];
                        double neighborFromSrc = curDistFromSrc * edge[1];
                        if (neighborFromSrc > distTo[neighborNode]) {
                            distTo[neighborNode] = neighborFromSrc;
                            pq.offer(new State(neighborNode, neighborFromSrc));
                        }
                    }
                }
            }
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PathWithMaximumProbability().new Solution();
        // put your test code here
        System.out.println(solution.maxProbability(5, new int[][]{
                {1, 4}, {2, 4}, {0, 4}, {0, 3}, {0, 2}, {2, 3}
        }, new double[]{
                0.37, 0.17, 0.93, 0.23, 0.39, 0.04
        }, 3, 4));
    }
}