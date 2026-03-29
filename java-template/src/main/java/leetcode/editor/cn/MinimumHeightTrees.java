package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MinimumHeightTrees {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            // 只有1个节点，没有边，返回该节点
            if (n == 1)
                return new ArrayList<>(List.of(0));
            // 构图
            List<Integer>[] graph = buildGraph(n, edges);
            // 找到所有的叶子节点(只有一个邻居的节点)
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < graph.length; i++)
                if (graph[i].size() == 1)
                    dq.offer(i);

            /*
             * 1. 删除所有叶子节点，同时将新的叶子节点加入列表
             * 相当于留下邻居多的节点做根节点,降低树的高度
             * 2. 当剩余节点小于等于2时，列表中的节点即为最小高度树的根节点
             * 为什么是剩余节点数小于等于2？
             * 如果剩余节点数为3，有可能都是最小高度树的根节点吗？
             * 不可能，因为是无环连通图，所以3个节点中必有1个节点有两条边，必有2个节点为叶子节点
             * 此时以有两条边的节点为根的树的高度为2，以叶子节点为根的树的高度为3
             * 故以有两条边的节点为根的树的高度必然小于以叶子节点为根的树的高度
             * */
            int nodeCount = n;
            while (nodeCount > 2) {
                int sz = dq.size();
                nodeCount -= sz;
                for (int i = 0; i < sz; i++) {
                    // 弹出叶子节点，注意这里是包装类型
                    Integer leave = dq.poll();
                    // 获取叶子节点的父节点
                    List<Integer> parent = graph[leave];
                    Integer p = parent.get(0);
                    // 从父节点邻居中删除该叶子节点，传包装类型删叶子节点，传int类型删该索引对应的值
                    graph[p].remove(leave);
                    // 若父节点也变成叶子节点,则加入队列
                    if (graph[p].size() == 1)
                        dq.offer(p);
                }
            }
            List<Integer> res = new ArrayList<>();
            while (!dq.isEmpty())
                res.add(dq.poll());
            return res;
        }

        List<Integer>[] buildGraph(int n, int[][] edges) {
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                graph[a].add(b);
                graph[b].add(a);
            }
            return graph;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 用BFS找出以每个节点为根的高度，取最小高度
     * 【Time Limit Exceeded】
     */
    class Solution1 {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer>[] graph = buildGraph(n, edges);
            int[] depths = new int[n];
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                depths[i] = bfs(graph, i);
                min = Math.min(min, depths[i]);
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < depths.length; i++)
                if (depths[i] == min)
                    res.add(i);
            return res;
        }

        int bfs(List<Integer>[] graph, int n) {
            Deque<Integer> dq = new ArrayDeque<>();
            boolean[] visited = new boolean[graph.length];
            dq.offer(n);
            visited[n] = true;
            int depth = 0;
            while (!dq.isEmpty()) {
                int sz = dq.size();
                for (int i = 0; i < sz; i++) {
                    Integer cur = dq.poll();
                    for (Integer neigh : graph[cur]) {
                        if (visited[neigh])
                            continue;
                        dq.offer(neigh);
                        visited[neigh] = true;
                    }
                }
                depth++;
            }
            return depth;
        }

        List<Integer>[] buildGraph(int n, int[][] edges) {
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                graph[a].add(b);
                graph[b].add(a);
            }
            return graph;
        }

    }


    public static void main(String[] args) {
        Solution solution = new MinimumHeightTrees().new Solution();
        // put your test code here
    }
}