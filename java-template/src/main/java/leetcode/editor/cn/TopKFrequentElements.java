package leetcode.editor.cn;

import java.util.*;

public class TopKFrequentElements {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路三：哈希表 + 桶排序
     * 1. 遍历nums，使用哈希表统计每个元素出现的次数
     * 2. 创建桶数组，将哈希表中的元素按照出现频率放入对应的桶中
     * 3. 从后往前遍历桶数组，收集前k个高频元素
     *
     * 时间复杂度：O(N)
     *   - 遍历nums统计频率：O(N)，N为nums的长度
     *   - 构建桶数组并填充元素：O(U)，U为不同元素的个数
     *   - 从后往前遍历桶数组收集结果：O(N)，桶的数量为N+1
     *   - 总时间复杂度：O(N)
     *
     * 空间复杂度：O(N)
     *   - 哈希表存储每个元素及其出现次数：O(U)
     *   - 桶数组及其内部列表存储元素：O(N)
     *   - 总空间复杂度：O(N)
     *
     * */
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // 使用哈希表统计每个元素出现的次数
            HashMap<Integer, Integer> valToFreq = new HashMap<>();
            // 遍历nums，使用哈希表统计每个元素出现的次数
            for (int num : nums)
                valToFreq.put(num, valToFreq.getOrDefault(num, 0) + 1);
            // 频率 -> 这个频率有哪些元素
            ArrayList<Integer>[] freqToVals = new ArrayList[nums.length + 1];
            for (Integer val : valToFreq.keySet()) {
                Integer freq = valToFreq.get(val);
                if (freqToVals[freq] == null)
                    freqToVals[freq] = new ArrayList<>();
                freqToVals[freq].add(val);
            }

            int[] res = new int[k];
            int p = 0;
            // 从后往前，收集频率前k高的元素
            for (int i = freqToVals.length - 1; i > 0; i--) {
                List<Integer> vals = freqToVals[i];
                if (vals == null)
                    continue;
                for (Integer val : vals) {
                    res[p++] = val;
                    if (p == k)
                        return res;
                }
            }
            return null;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路二：哈希表 + 堆排序
     * 1. 遍历nums，使用哈希表统计每个元素出现的次数
     * 2. 使用大小为k的小顶堆，将哈希表中的元素按照出现次数从大到小排序
     * 3. 返回排序后的前k个元素
     *
     * 时间复杂度：O(N + U log k)
     *   - 遍历nums统计频率：O(N)，N为nums的长度
     *   - 维护大小为k的小顶堆：O(U log k)，U为不同元素的个数，每次入堆和出堆操作为O(log k)
     *   - 将堆中元素转为数组：O(k)
     *   - 总时间复杂度：O(N + U log k)，其中U ≤ N，最坏情况下为O(N log k)
     *
     * 空间复杂度：O(U + k)
     *   - 哈希表存储每个元素及其出现次数：O(U)
     *   - 优先队列存储最多k个高频元素：O(k)
     *   - 总空间复杂度：O(U + k)，由于k ≤ U，通常简化为O(U)
     * */
    class Solution2 {
        public int[] topKFrequent(int[] nums, int k) {
            // 使用哈希表统计每个元素出现的次数
            HashMap<Integer, Integer> map = new HashMap<>();
            // 遍历nums，使用哈希表统计每个元素出现的次数
            for (int num : nums)
                map.put(num, map.getOrDefault(num, 0) + 1);
            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                pq.offer(entry);
                if (pq.size() > k)
                    pq.poll();
            }
            return pq.stream().mapToInt(Map.Entry::getKey).toArray();
        }
    }

    /*
     * 思路一：哈希表 + 排序
     * 1. 遍历nums，使用哈希表统计每个元素出现的次数
     * 2. 将哈希表中的元素按照出现次数从大到小排序
     * 3. 返回排序后的前k个元素
     *
     * 时间复杂度：O(N + U log U)
     *   - 遍历nums统计频率：O(N)，N为nums的长度
     *   - 将entrySet转为ArrayList：O(U)，U为不同元素的个数
     *   - 对ArrayList排序：O(U log U)
     *   - 取前k个元素：O(k)
     *   - 总时间复杂度：O(N + U log U)，其中U ≤ N，最坏情况下为O(N log N)
     *
     * 空间复杂度：O(U)
     *   - 哈希表存储每个元素及其出现次数：O(U)
     *   - ArrayList存储哈希表的entry：O(U)
     * */
    class Solution1 {
        public int[] topKFrequent(int[] nums, int k) {
            // 使用哈希表统计每个元素出现的次数
            HashMap<Integer, Integer> map = new HashMap<>();
            // 遍历nums，使用哈希表统计每个元素出现的次数
            for (int num : nums)
                map.put(num, map.getOrDefault(num, 0) + 1);
            // Stream API排序
            return map.entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                    .limit(k)
                    .mapToInt(Map.Entry::getKey)
                    .toArray();
        }
    }


    public static void main(String[] args) {
        Solution solution = new TopKFrequentElements().new Solution();
        // put your test code here

    }
}