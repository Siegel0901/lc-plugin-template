package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PossibleBipartition {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        boolean flag = true;
        boolean[] visited;
        boolean[] color;

        public boolean possibleBipartition(int n, int[][] dislikes) {
            // 节点编号为1~n
            visited = new boolean[n + 1];
            color = new boolean[n + 1];
            List<Integer>[] graph = buildGraph(n, dislikes);
            // 遍历所有未访问节点
            for (int i = 1; i <= n; i++)
                if (!visited[i])
                    // dfs(graph, i);
                    bfs(graph, i);
            return flag;
        }

        void dfs(List<Integer>[] graph, int start) {
            if (!flag)
                return;
            visited[start] = true;
            for (Integer next : graph[start]) {
                if (visited[next]) {
                    if (color[next] == color[start])
                        flag = false;
                } else {
                    color[next] = !color[start];
                    dfs(graph, next);
                }
            }
        }

        void bfs(List<Integer>[] graph, int start) {
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(start);
            visited[start] = true;
            while (!q.isEmpty()) {
                Integer poll = q.poll();
                for (Integer next : graph[poll]) {
                    if (visited[next]) {
                        if (color[next] == color[poll]) {
                            flag = false;
                            return;
                        }
                    } else {
                        color[next] = !color[poll];
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }

        // 建图函数
        List<Integer>[] buildGraph(int n, int[][] dislikes) {
            // 节点编号为1~n
            List<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++)
                graph[i] = new ArrayList<>();
            for (int[] edge : dislikes) {
                int a = edge[0], b = edge[1];
                graph[a].add(b);
                graph[b].add(a);
            }
            return graph;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PossibleBipartition().new Solution();
        // put your test code here

    }
}