package leetcode.editor.cn;

import java.util.*;

public class NetworkDelayTime {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class State {
            int node;
            // 记录当前节点到原点的距离
            int distFromSrc;

            State(int node, int distFromSrc) {
                this.node = node;
                this.distFromSrc = distFromSrc;
            }
        }

        public int networkDelayTime(int[][] times, int n, int k) {
            // 构建图
            List<int[]>[] graph = buildGraph(times, n);
            // 使用dijkstra算法得到单源最短路径
            int[] distTo = dijkstra(graph, k);
            // 获取最长的单源最短路径
            int res = 0;
            for (int i = 1; i <= n; i++) {
                if (distTo[i] == Integer.MAX_VALUE)
                    return -1;
                res = Math.max(res, distTo[i]);
            }
            return res;
        }

        int[] dijkstra(List<int[]>[] graph, int src) {
            // distTo数组记录当前状态下源点到每个节点已知的最短距离
            int[] distTo = new int[graph.length];
            // 初始化
            Arrays.fill(distTo, Integer.MAX_VALUE);
            // 比较距离的小顶堆
            Queue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distFromSrc));
            // 更新源点到自身的最短距离
            distTo[src] = 0;
            // 源点状态入队
            pq.offer(new State(src, 0));
            // bfs遍历图
            while (!pq.isEmpty()) {
                // 取出距离最小的节点
                State minDistNode = pq.poll();
                int curNode = minDistNode.node;
                int curDistFromSrc = minDistNode.distFromSrc;
                /*
                 * 出队后检查:
                 * bfs过程中会有相同节点不同距离的状态加入队列
                 * 出队过程中，只有两种情况curDistFromSrc >= distTo[curNode]
                 * 1. 情况一:curDistFromSrc == distTo[curNode]
                 * 1.1. 当节点更新最短距离时，节点状态与已知的最短距离被设为相等，是最新的状态，必须继续处理其邻居，扩大搜索范围
                 * 1.2. 如加入源点(src,0),distTo[src]=0,需要处理其邻居
                 * 2. 情况二:curDistFromSrc > distTo[curNode]
                 * 2.1. 这是由于每次贪心选择最小距离节点出队造成的
                 * 2.2. 如先加入(1,4),后加入(1,2),肯定是先处理(1,2),这时(1,4)成了旧状态,不必处理
                 * 3. 为什么不会出现 curDistFromSrc < distTo[curNode]的情况?
                 * 3.1. 因为nextDistFromSrc < distTo[nextNode]时，新状态入队，更新了distTo[nextNode] = nextDistFromSrc
                 * 3.2. 对已知最短距离的节点，不会加入nextDistFromSrc >= distTo[nextNode]的状态
                 * 3.3. 对最短距离不确定的节点，第一次更新最短距离时，distTo[nextNode] = nextDistFromSrc
                 * 3.4. 对已知最短距离的节点，若存在更短的距离，则更新distTo[nextNode] = nextDistFromSrc
                 * */
                if (curDistFromSrc == distTo[curNode]) {
                    // 遍历邻居
                    for (int[] edge : graph[curNode]) {
                        // 获取节点
                        int nextNode = edge[0];
                        // 邻居到源点的距离 = 当前节点到源点的距离 + 当前节点到邻居的距离
                        int nextDistFromSrc = curDistFromSrc + edge[1];
                        /*
                         * 什么时候需要更新源点到邻居的最短距离？
                         * 1. 源点到邻居的最短距离不确定
                         * 2. 源点到邻居的最短距离已知，但存在更短的路径
                         * 总结：存在路径或存在更短路径
                         * */
                        if (nextDistFromSrc < distTo[nextNode]) {
                            // 更新邻居到源点的最短距离
                            distTo[nextNode] = nextDistFromSrc;
                            // 将邻居的新状态加入队列
                            pq.offer(new State(nextNode, nextDistFromSrc));
                        }
                    }
                }
            }
            return distTo;
        }

        List<int[]>[] buildGraph(int[][] times, int n) {
            List<int[]>[] graph = new List[n + 1];
            for (int i = 1; i <= n; i++)
                graph[i] = new ArrayList<>();
            for (int[] edge : times)
                graph[edge[0]].add(new int[]{edge[1], edge[2]});
            return graph;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NetworkDelayTime().new Solution();
        // put your test code here

    }
}