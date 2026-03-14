package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MinCostToConnectAllPoints {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：Kruskal算法计算最小生成树
         * 1. 设置边集合
         * 2. 对边集合按照边的权重进行升序排序
         * 3. 遍历所有边，利用并查集每次加入最小的边构成连通分量
         * 4. 若边的两个节点不是同一个连通分量，则联合两个节点构成同一个连通分量，累计权重
         * 5. 所有边遍历完之后得到最小生成树及其对应权重
         */
        public int minCostConnectPoints(int[][] points) {
            // n为节点个数
            int n = points.length;
            // 边集合，每条边的有三元素{a,b,ab之间的曼哈顿距离}
            List<int[]> edges = new ArrayList<>();
            // 不重复计算边
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    edges.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});
            // 对边集合按照距离进行升序排序
            List<int[]> sorted = edges.stream().sorted(Comparator.comparingInt(a -> a[2])).collect(Collectors.toCollection(ArrayList::new));
            // 并查集
            UF uf = new UF(n);
            // 最小生成树权重
            int mst = 0;
            // 遍历边集合
            for (int[] edge : sorted) {
                // 取出节点
                int a = edge[0];
                int b = edge[1];
                // 节点构成的边的权重
                int weight = edge[2];
                // 判断两节点是否已经是同一个连通分量
                if (uf.connect(a, b))
                    continue;
                // ab加入mst
                uf.union(a, b);
                mst += weight;
            }
            return mst;
        }

        class UF {
            private int count;
            private int[] parent;
            private int[] size;

            public UF(int n) {
                count = n;
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);

                if (rootP == rootQ)
                    return;

                if (size[rootP] < size[rootQ]) {
                    parent[rootP] = rootQ;
                    size[rootQ] += size[rootP];
                } else {
                    parent[rootQ] = rootP;
                    size[rootP] += size[rootQ];
                }

                count--;
            }

            public boolean connect(int p, int q) {
                return find(p) == find(q);
            }

            public int find(int x) {
                if (parent[x] != x)
                    parent[x] = find(parent[x]);
                return parent[x];
            }

            public int count() {
                return count;
            }

            public int size(int x) {
                return size[find(x)];
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinCostToConnectAllPoints().new Solution();
        // put your test code here
        solution.minCostConnectPoints(new int[][]{
                {0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}
        });
    }
}