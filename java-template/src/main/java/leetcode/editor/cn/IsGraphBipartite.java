package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Queue;

public class IsGraphBipartite {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：BFS
     * 1. BFS遍历过程中给节点上色
     * 2. 对于当前节点的邻近节点
     * 2.1. 未被上色：上色，与当前节点颜色相反
     * 2.2. 已上色过：查看颜色，若与当前节点颜色相同，则不是二分图
     */
    class Solution {
        // 判断是否为二分图
        boolean flag = true;
        // 记录节点是否已被上色
        boolean[] colored;
        // 记录节点的上色情况
        boolean[] color;

        public boolean isBipartite(int[][] graph) {
            // n为节点数
            int n = graph.length;
            // 初始化
            colored = new boolean[n];
            color = new boolean[n];
            // bfs遍历所有未被上色的节点
            for (int i = 0; i < graph.length; i++)
                if (!colored[i])
                    bfs(graph, i);
            // 返回是否为二分图
            return flag;
        }

        void bfs(int[][] graph, int start) {
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(start);
            colored[start] = true;
            while (!q.isEmpty()) {
                Integer poll = q.poll();
                for (int next : graph[poll]) {
                    // 对已上色的节点，判断节点颜色
                    if (colored[next]) {
                        if (color[next] == color[poll]) {
                            flag = false;
                            return;
                        }
                    } else {
                        // 对未上色的节点进行上色
                        color[next] = !color[poll];
                        colored[next] = true;
                        q.offer(next);
                    }
                }
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：DFS
     * 1. DFS遍历过程中给节点上色
     * 2. 对于当前节点的邻近节点
     * 2.1. 未被访问过：上色，与当前节点颜色相反
     * 2.2. 被访问过：查看颜色，若与当前节点颜色相同，则不是二分图
     */
    class Solution1 {
        // 判断是否为二分图
        boolean flag = true;
        // 记录节点的访问情况
        boolean[] visited;
        // 记录节点的上色情况
        boolean[] color;

        public boolean isBipartite(int[][] graph) {
            // n为节点数
            int n = graph.length;
            // 初始化
            visited = new boolean[n];
            color = new boolean[n];
            // dfs遍历所有未遍历过的节点
            for (int i = 0; i < graph.length; i++)
                if (!visited[i])
                    dfs(graph, i);
            // 返回是否为二分图
            return flag;
        }

        void dfs(int[][] graph, int v) {
            // 如果不是二分图,则没必要继续遍历
            if (!flag)
                return;
            // 记录当前节点的访问状态
            visited[v] = true;
            // 遍历当前节点的邻接节点
            for (int next : graph[v]) {
                // 如果被访问过,判断颜色
                if (visited[next]) {
                    if (color[next] == color[v])
                        flag = false;
                } else {
                    // 未被访问过,则上色
                    color[next] = !color[v];
                    // 继续遍历其他邻接节点
                    dfs(graph, next);
                }
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new IsGraphBipartite().new Solution();
        // put your test code here

    }
}