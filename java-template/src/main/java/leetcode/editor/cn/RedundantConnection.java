package leetcode.editor.cn;

public class RedundantConnection {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：并查集
         * 1. 题目所给的图是一个连通分量
         * 2. 可以将图中的节点构建成一个并查集
         * 3. 若构建过程中连通分量数量没有发生变化，则该边可删除
         * 4. 由于是按照edges中边的先后顺序构建，且只有一条多余的边
         * 5. 所以遇到的第一个已连通的两个节点所构成的边就是edges中最后出现的那个答案
         */
        public int[] findRedundantConnection(int[][] edges) {
//            int n = edges.length;
//            boolean[] canDelete = new boolean[n];
//            UF uf = new UF(n);
//            int count = uf.count();
//            for (int i = 0; i < n; i++) {
//                int a = edges[i][0], b = edges[i][1];
//                uf.union(a, b);
//                if (count == uf.count())
//                    canDelete[i] = true;
//                count = uf.count();
//            }
//            for (int i = n - 1; i >= 0; i--)
//                if (canDelete[i])
//                    return edges[i];
//            return new int[]{};
            int n = edges.length;
            UF uf = new UF(n);
            for (int i = 0; i < n; i++) {
                int a = edges[i][0], b = edges[i][1];
                if (uf.connect(a, b))
                    return edges[i];
                uf.union(a, b);
            }
            return new int[]{};
        }

        class UF {
            private int count;
            private int[] parent;
            private int[] size;

            public UF(int n) {
                count = n;
                parent = new int[n + 1];
                size = new int[n + 1];
                for (int i = 1; i <= n; i++) {
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
        Solution solution = new RedundantConnection().new Solution();
        // put your test code here

    }
}