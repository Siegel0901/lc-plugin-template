package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingRainWaterIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：BFS + 优先队列
     * 1. 水位到达height[i][j]的高度时，会往四周淹掉高度小于height[i][j]的位置。
     * 2. 接水量 = 水位高度 - 位置高度（位置高度 < 水位高度）
     * 3. 从高度低的边界开始往四周扩散（如果先从水位高的边界开始，水如果有到位置低的边界的路径的话水就流走了）
     * 3.1. 遇到比当前水位高度低的位置：
     * 3.1.1. 记录接水量，同时以水位高度加入队列，因为已经被接满了
     * 3.1.2. 并且该位置不可再访问（从低水位开始扩散，高水位遇到该位置水也会从低水位流走）
     * 3.2. 遇到比当前水位高度高的位置：
     * 3.2.1. 无法淹掉，只能以该位置的高度加入队列
     * 时间复杂度：O(m*n*log(m*n))
     * 空间复杂度：O(m*n)
     * */
    class Solution {
        public int trapRainWater(int[][] heightMap) {
            int m = heightMap.length, n = heightMap[0].length;
            if (m <= 2 || n <= 2)
                return 0;
            boolean[][] visited = new boolean[m][n];
            // 每个状态用int[]{高度,横坐标,纵坐标}表示，创建以高度为比较元素的小顶堆，每次取出高度最小的状态
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            // 将边界加入优先队列（边界不会接到水）
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                        pq.offer(new int[]{heightMap[i][j], i, j});
                        // 避免重复访问边界
                        visited[i][j] = true;
                    }
                }
            }
            // 方向数组
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            // 记录结果
            int res = 0;
            while (!pq.isEmpty()) {
                // 获取当前状态
                int[] curr = pq.poll();
                // 当前水位高度
                int h = curr[0];
                // 当前水位横坐标
                int x = curr[1];
                // 当前水位纵坐标
                int y = curr[2];
                // 遍历邻居，尝试淹掉
                for (int[] dir : dirs) {
                    // 邻居横坐标
                    int nx = x + dir[0];
                    // 邻居纵坐标
                    int ny = y + dir[1];
                    // 越界or已访问则跳过
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny])
                        continue;
                    // 若邻居高度低于当前水位,则淹掉,记录接水量
                    if (heightMap[nx][ny] < h)
                        res += h - heightMap[nx][ny];
                    // 加入优先队列,高度取水位和该邻居高度的最大值
                    pq.offer(new int[]{Math.max(h, heightMap[nx][ny]), nx, ny});
                    // 设为已访问
                    visited[nx][ny] = true;
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new TrappingRainWaterIi().new Solution();
        // put your test code here

    }
}