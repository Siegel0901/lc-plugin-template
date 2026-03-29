package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class JumpGameIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        boolean canReach = false;
        List<Integer>[] graph;
        boolean[] visited;

        public boolean canReach(int[] arr, int start) {
            buildGraph(arr);
            visited = new boolean[arr.length];
            dfs(arr, start);
            return canReach;
        }

        void dfs(int[] arr, int start) {
            if (canReach)
                return;
            if (visited[start])
                return;
            if (arr[start] == 0) {
                canReach = true;
                return;
            }
            visited[start] = true;
            for (int neigh : graph[start])
                dfs(arr, neigh);
        }

        void buildGraph(int[] arr) {
            int n = arr.length;
            graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] neighs = {i + arr[i], i - arr[i]};
                for (int neigh : neighs) {
                    if (neigh < 0 || neigh >= n)
                        continue;
                    graph[i].add(neigh);
                }
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        public boolean canReach(int[] arr, int start) {
            List<Integer>[] graph = buildGraph(arr);
            Deque<Integer> dq = new ArrayDeque<>();
            boolean[] visited = new boolean[arr.length];
            dq.offer(start);
            visited[start] = true;
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                if (arr[cur] == 0)
                    return true;
                for (int neigh : graph[cur]) {
                    if (visited[neigh])
                        continue;
                    dq.offer(neigh);
                    visited[neigh] = true;
                }
            }
            return false;
        }

        List<Integer>[] buildGraph(int[] arr) {
            int n = arr.length;
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] neighs = {i + arr[i], i - arr[i]};
                for (int neigh : neighs) {
                    if (neigh < 0 || neigh >= n)
                        continue;
                    graph[i].add(neigh);
                }
            }
            return graph;
        }
    }


    public static void main(String[] args) {
        Solution solution = new JumpGameIii().new Solution();
        // put your test code here

    }
}