package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 路径集合
        List<List<Integer>> res = new ArrayList<>();
        // 单个路径
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 从节点0开始遍历图
            dfs(graph, 0);
            return res;
        }

        void dfs(int[][] graph, int s) {
            // 当前节点加入路径
            path.add(s);
            int n = graph.length;
            // 若s是最后一个节点
            if (s == n - 1) {
                // 路径加入结果
                res.add(new ArrayList<>(path));
                // 回到上一个状态
                path.remove(path.size() - 1);
                return;
            }
            // 以s的邻居为起点继续遍历
            for (int v : graph[s])
                dfs(graph, v);
            // 遍历结束后删除当前节点,回到上一个状态
            path.remove(path.size() - 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new AllPathsFromSourceToTarget().new Solution();
        // put your test code here

    }
}