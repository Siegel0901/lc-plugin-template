package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class CourseScheduleIi {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：DFS
     * 1. 考察拓扑排序
     * 2. DFS进行后序遍历的结果再逆序，就是拓扑排序
     * 3. 因为后序遍历做到了先遍历完所有邻接节点，最后遍历自己
     * 4. 即先完成依赖于本课程的所有课程，最后完成本课程
     * 5. 逆序后得到的就是正确的课程学习顺序
     */
    class Solution {
        boolean hasCycle = false;
        boolean[] visited;
        boolean[] onPath;
        List<Integer> postOrder = new ArrayList<>();

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];
            // 确保每个节点都被遍历到，最后都会加入postOrder
            for (int i = 0; i < numCourses; i++)
                dfs(graph, i);
            // 存在环，则无拓扑排序
            if (hasCycle)
                return new int[]{};
            // 索引倒序实现翻转后序遍历结果并转为int[]类型
            return IntStream.range(0, postOrder.size())
                    .map(i -> postOrder.get(postOrder.size() - 1 - i))
                    .toArray();
        }

        void dfs(List<Integer>[] graph, int s) {
            if (onPath[s])
                hasCycle = true;
            if (visited[s] || hasCycle)
                return;
            // 前序遍历位置
            onPath[s] = true;
            visited[s] = true;
            // 遍历邻接节点
            for (Integer next : graph[s])
                dfs(graph, next);
            // 后序遍历位置
            onPath[s] = false;
            postOrder.add(s);
        }

        List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new ArrayList[numCourses];
            for (int i = 0; i < numCourses; i++)
                graph[i] = new ArrayList<>();
            for (int[] edge : prerequisites) {
                // from->to
                int from = edge[1], to = edge[0];
                graph[from].add(to);
            }
            return graph;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：BFS
     * 1. 考察拓扑排序
     * 2. 通过BFS判断图是否存在环的过程中，使用了inDegree数组
     * 3. 遍历过程中入度为0的节点入队的顺序就是拓扑排序的顺序
     */
    class Solution1 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            int[] inDegree = new int[numCourses];
            for (int[] edge : prerequisites) {
                // from->to
                int from = edge[1], to = edge[0];
                inDegree[to]++;
            }
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++)
                if (inDegree[i] == 0)
                    q.offer(i);
            // 记录拓扑排序结果
            int[] res = new int[numCourses];
            int count = 0;
            while (!q.isEmpty()) {
                Integer poll = q.poll();
                res[count++] = poll;
                for (Integer next : graph[poll])
                    if (--inDegree[next] == 0)
                        q.offer(next);
            }
            if (count != numCourses)
                return new int[]{};
            return res;
        }

        List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new ArrayList[numCourses];
            for (int i = 0; i < numCourses; i++)
                graph[i] = new ArrayList<>();
            for (int[] edge : prerequisites) {
                // from->to
                int from = edge[1], to = edge[0];
                graph[from].add(to);
            }
            return graph;
        }
    }


    public static void main(String[] args) {
        Solution solution = new CourseScheduleIi().new Solution();
        // put your test code here

    }
}