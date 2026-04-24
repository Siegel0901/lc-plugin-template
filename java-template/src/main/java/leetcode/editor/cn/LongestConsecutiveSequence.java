package leetcode.editor.cn;

import java.util.*;

public class LongestConsecutiveSequence {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路四：哈希表记录区间长度
     * 核心思想：用一个HashMap记录每个数所在区间的长度，只在区间的端点更新
     * 1. 遍历每个数num
     * 2. 获取左右边界的信息
     * 3. 计算当前区间总长度
     * 4. 更新端点的区间长度
     * 5. 更新全局最大值
     * 注意：只需要更新区间端点的长度，中间的元素不会再被当做端点
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution {
        public int longestConsecutive(int[] nums) {
            int max = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                // num已经处理过，不会增加区间长度，跳过
                if (map.containsKey(num))
                    continue;
                // 获取num-1作为右端点的区间长度
                int left = map.getOrDefault(num - 1, 0);
                // 获取num+1作为左端点的区间长度
                int right = map.getOrDefault(num + 1, 0);
                // 计算num加入左区间和右区间的区间长度
                int curLen = left + right + 1;
                // 标记已处理
                map.put(num, curLen);
                // 更新左端点
                map.put(num - left, curLen);
                // 更新右端点
                map.put(num + right, curLen);
                // 记录最大值
                max = Math.max(max, curLen);
            }
            return max;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路三：并查集
     * 1. 将连续数字合并到同一个集合中
     * 2. 最大并查集的长度即为最长连续序列的长度
     * 3. 遍历每个数num
     * 3.1. 若num+1存在，合并num和num+1
     * 3.2. 若num-1存在，合并num和num-1
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution3 {
        public int longestConsecutive(int[] nums) {
            // 将所有元素加入集合去重
            Set<Integer> set = new HashSet<>();
            for (int num : nums)
                set.add(num);
            // 构建并查集
            UF uf = new UF(set.size());
            // 为所有元素构建索引，处理元素为负数的情况
            Map<Integer, Integer> map = new HashMap<>();
            int idx = 0;
            for (int num : set)
                map.put(num, idx++);
            // 遍历集合中所有元素,判断num-1和num+1是否在集合中，在且未合并则合并
            for (int num : set)
                for (int i : new int[]{num - 1, num + 1})
                    if (set.contains(i) && !uf.connect(map.get(num), map.get(i)))
                        uf.union(map.get(num), map.get(i));
            // 最大集合的长度即为最长连续序列的长度
            int max = 0;
            for (int num : set)
                max = Math.max(max, uf.size(map.get(num)));
            return max;
        }

        /*
         * 并查集（Union-Find）数据结构实现
         * 支持路径压缩和按秩合并优化
         * */
        class UF {
            private int count;
            private int[] parent;
            private int[] size;

            /**
             * 初始化并查集
             *
             * @param n 节点数量，每个节点初始为独立的集合
             */
            public UF(int n) {
                count = n;
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            /**
             * 合并两个节点所在的集合
             * 使用按秩合并优化,将较小的树挂到较大的树下
             *
             * @param p 第一个节点
             * @param q 第二个节点
             */
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

            /**
             * 判断两个节点是否连通（是否在同一集合中）
             *
             * @param p 第一个节点
             * @param q 第二个节点
             * @return 如果两个节点连通返回 true，否则返回 false
             */
            public boolean connect(int p, int q) {
                return find(p) == find(q);
            }

            /**
             * 查找节点的根节点，并进行路径压缩优化
             * 路径压缩：将查找路径上的所有节点直接指向根节点
             *
             * @param x 要查找的节点
             * @return 节点 x 所在集合的根节点
             */
            public int find(int x) {
                if (parent[x] != x)
                    parent[x] = find(parent[x]);
                return parent[x];
            }

            /**
             * 获取当前连通分量的数量
             *
             * @return 连通分量的数量
             */
            public int count() {
                return count;
            }

            /**
             * 获取节点所在集合的大小
             *
             * @param x 目标节点
             * @return 节点 x 所在集合中的节点数量
             */
            public int size(int x) {
                return size[find(x)];
            }
        }
    }

    /*
     * 思路二：哈希集合 + 筛选起点
     * 1. 将所有元素加入HashSet去重
     * 2. 找连续序列的起点：若当前元素为num，若不存在num-1，则num为起点
     * 3. 计算该起点的最长连续序列
     * 4. 记录最长连续序列
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * */
    class Solution2 {
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums)
                set.add(num);
            int max = 0;
            for (int num : set) {
                if (set.contains(num - 1))
                    continue;
                int count = 0;
                int cur = num;
                while (set.contains(cur)) {
                    count++;
                    cur++;
                }
                max = Math.max(max, count);
            }
            return max;
        }
    }

    /*
     * 思路一：排序
     * 1. 将nums排序
     * 2. 排完序后统计最长连续数字的长度
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * */
    class Solution1 {
        public int longestConsecutive(int[] nums) {
            if (nums.length <= 1)
                return nums.length;
            Arrays.sort(nums);
            int max = 1;
            int count = 1;
            int num = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (num == nums[i])
                    continue;
                if (nums[i] == num + 1)
                    count++;
                else {
                    max = Math.max(max, count);
                    count = 1;
                }
                num = nums[i];
            }
            return Math.max(max, count);
        }
    }


    public static void main(String[] args) {
        Solution solution = new LongestConsecutiveSequence().new Solution();
        // put your test code here
        solution.longestConsecutive(new int[]{1, 0, 1, 2});
    }
}