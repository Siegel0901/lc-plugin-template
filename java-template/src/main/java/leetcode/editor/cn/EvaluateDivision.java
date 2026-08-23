package leetcode.editor.cn;

import java.util.*;

public class EvaluateDivision {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路一：建图 + BFS
     * 1. a/b=2即a->b权值为2，b->a权值为1/2。
     * 2. x/y的值，即是否存在一条从x到y的路径，若存在，则路径上所有边的权值相乘就是x/y的值。
     * */
    class Solution {
        class Edge {
            String node;
            double weight;

            public Edge(String node, double weight) {
                this.node = node;
                this.weight = weight;
            }
        }

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // 构图
            Map<String, List<Edge>> graph = buildGraph(equations, values);
            // 结果数组
            double[] res = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                // 获取起点和终点
                List<String> query = queries.get(i);
                // bfs计算路径乘积
                String start = query.get(0), end = query.get(1);
                // 存入结果数组
                res[i] = bfs(graph, start, end);
            }
            // 返回结果
            return res;
        }

        private Map<String, List<Edge>> buildGraph(List<List<String>> equations, double[] values) {
            // 邻接表
            HashMap<String, List<Edge>> graph = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                // 获取a/b的值
                double w = values[i];
                // 获取a和b
                List<String> equation = equations.get(i);
                String a = equation.get(0), b = equation.get(1);
                // a -> b, b -> a
                graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new Edge(b, w));
                graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new Edge(a, 1.0 / w));
            }
            return graph;
        }

        private double bfs(Map<String, List<Edge>> graph, String start, String end) {
            // start和end不存在
            if (!graph.containsKey(start) || !graph.containsKey(end))
                return -1.0;
            // start即为end
            if (start.equals(end))
                return 1.0;

            // bfs队列
            Queue<String> q = new ArrayDeque<>();
            // set记录节点是否访问
            HashSet<String> visited = new HashSet<>();
            // 初始节点start
            q.offer(start);
            visited.add(start);

            // key为节点ID，value记录从start到该节点的路径乘积
            Map<String, Double> weight = new HashMap<>();
            weight.put(start, 1.0);

            while (!q.isEmpty()) {
                // 头节点出队
                String cur = q.poll();
                // 访问该节点的所有邻居
                for (Edge neigh : graph.get(cur)) {
                    // 邻居已访问则跳过
                    if (visited.contains(neigh.node))
                        continue;
                    // 更新start到该邻居节点的路径乘积 = start到当前节点路径乘积 * 当前节点到邻居节点的边权值
                    weight.put(neigh.node, weight.get(cur) * neigh.weight);
                    // 如果邻居节点即为end节点,则返回start到end的距离
                    if (neigh.node.equals(end))
                        return weight.get(end);
                    // 标记邻居节点已访问
                    visited.add(neigh.node);
                    // 邻居节点入队
                    q.offer(neigh.node);
                }
            }
            return -1.0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new EvaluateDivision().new Solution();
        // put your test code here

    }
}