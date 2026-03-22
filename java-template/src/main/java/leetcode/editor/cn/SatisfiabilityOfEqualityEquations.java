package leetcode.editor.cn;

public class SatisfiabilityOfEqualityEquations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：并查集
         * 1. 先将==的变量相连接
         * 2. 再判断!=的变量是否破坏了连通性
         */
        public boolean equationsPossible(String[] equations) {
            UF uf = new UF(26);
            for (String equation : equations) {
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                if (equation.charAt(1) == '=') {
                    uf.union(a, b);
                }
            }
            for (String equation : equations) {
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                if (equation.charAt(1) == '!')
                    if (uf.connect(a, b))
                        return false;
            }
            return true;
        }

        class UF {
            // 连通分量个数
            private int count;
            // 记录每个节点的父节点
            private int[] parent;
            // 记录连通分量的节点个数
            private int[] size;

            // n为图中的节点个数
            public UF(int n) {
                count = n;
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            // 连通节点p和q
            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);

                if (rootP == rootQ)
                    return;
                // 将节点数小的根节点接到节点数大的根节点下
                if (size[rootP] < size[rootQ]) {
                    parent[rootP] = rootQ;
                    size[rootQ] += size[rootP];
                } else {
                    parent[rootQ] = rootP;
                    size[rootP] += size[rootQ];
                }
                // 连通分量数-1
                count--;
            }

            // 判断两个节点是否连通
            public boolean connect(int p, int q) {
                return find(p) == find(q);
            }

            // 路径压缩寻找根节点
            public int find(int x) {
                // 若当前节点的父节点不是根节点
                if (parent[x] != x)
                    // 当前节点的父节点设为父节点的根节点
                    parent[x] = find(parent[x]);
                // 返回根节点
                return parent[x];
            }

            // 返回连通分量个数
            public int count() {
                return count;
            }

            // 返回x所在连通分量的节点数
            public int size(int x) {
                return size[find(x)];
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SatisfiabilityOfEqualityEquations().new Solution();
        // put your test code here

    }
}