package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路一：BFS
     * 1. 利用inDegree数组记录每个节点的入度
     * 2. 每次将入度为0的节点加入队列
     * 3. 弹出队头节点，将队头节点指向的节点入度-1
     * 4. 重复2,3，直至队列为空
     * 5. 若遍历结束后，存在没有被访问的节点，则该节点最后的入度不为0，存在环
     */
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 构图
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            // 构建inDegree数组
            int[] inDegree = new int[numCourses];
            for (int[] edge : prerequisites) {
                // 学完from才能学to:from->to
                int from = edge[1], to = edge[0];
                // 需要添加from到to的有向边，记录to的入度
                inDegree[to]++;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            // 将入度为0的节点加入队列
            for (int i = 0; i < numCourses; i++)
                if (inDegree[i] == 0)
                    queue.offer(i);

            // 记录访问过的节点个数
            int count = 0;
            // BFS
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                // 计数
                count++;
                // poll的所有邻接节点入度-1
                for (Integer next : graph[poll])
                    // 若入度减为0则入队
                    if (--inDegree[next] == 0)
                        queue.offer(next);
            }
            // 所有节点都被访问，则无环
            return count == numCourses;
        }

        List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new ArrayList[numCourses];
            // 构建每个节点及其邻接表
            for (int i = 0; i < numCourses; i++)
                graph[i] = new ArrayList<>();
            // 填充邻接表
            for (int[] edge : prerequisites) {
                // 学完from才能学to:from->to
                int from = edge[1], to = edge[0];
                // 添加from到to的有向边
                graph[from].add(to);
            }
            return graph;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：DFS
     * 1. 用DFS遍历所有的路径
     * 2. 若遍历过程中遇到路径上有两个相同的节点，则说明有环
     */
    class Solution1 {
        // 记录是否存在环
        boolean hasCycle = false;
        // 记录节点是否访问
        boolean[] visited;
        // 记录当前路径
        boolean[] onPath;

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 构图
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            // 初始化
            onPath = new boolean[numCourses];
            visited = new boolean[numCourses];
            // 每个节点都需要做一遍起始节点
            for (int i = 0; i < numCourses; i++)
                dfs(graph, i);
            // 如果没有环则说明可以完成所有课程
            return !hasCycle;
        }

        List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new ArrayList[numCourses];
            // 构建每个节点及其邻接表
            for (int i = 0; i < numCourses; i++)
                graph[i] = new ArrayList<>();
            // 填充邻接表
            for (int[] edge : prerequisites) {
                // 学完from才能学to:from->to
                int from = edge[1], to = edge[0];
                // 添加from到to的有向边
                graph[from].add(to);
            }
            return graph;
        }

        void dfs(List<Integer>[] graph, int s) {
            // 存在环
            if (hasCycle)
                return;
            // 路径上遇到相同节点
            if (onPath[s]) {
                hasCycle = true;
                return;
            }
            // 访问过的节点无需重复验证以该节点出发是否构成环
            if (visited[s])
                return;
            // 记录当前节点被访问过
            visited[s] = true;
            // 将当前节点加入路径
            onPath[s] = true;
            // 访问与s邻接的所有节点
            for (int v : graph[s])
                dfs(graph, v);
            // 从路径中移除当前节点
            onPath[s] = false;
        }
    }


    public static void main(String[] args) {
        Solution solution = new CourseSchedule().new Solution();
        // put your test code here
    }
}