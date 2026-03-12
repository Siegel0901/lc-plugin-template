package leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LfuCache {

    //leetcode submit region begin(Prohibit modification and deletion)
    class LFUCache {
        // KV表:key到val的映射
        Map<Integer, Integer> keyToVal;
        // KF表:key到freq的映射
        Map<Integer, Integer> keyToFreq;
        /*
         * FK表:freq到key的映射
         * 需要满足以下条件:
         * 1. freq对key是一对多,相同freq的key可能有多个
         * 2. key列表存在时序,便于快速查找最旧的key
         * 3. 需要快速删除任意一个key,因为如果被访问了该key的freq需要+1
         * */
        Map<Integer, LinkedHashSet<Integer>> freqToKeys;
        // 记录当前最小频次
        int minFreq;
        // 最大容量
        int cap;


        public LFUCache(int capacity) {
            keyToVal = new HashMap<>();
            keyToFreq = new HashMap<>();
            freqToKeys = new HashMap<>();
            cap = capacity;
            minFreq = 0;
        }

        public int get(int key) {
            // key不存在返回-1
            if (!keyToVal.containsKey(key))
                return -1;
            // 增加key对应的freq
            increaseFreq(key);
            // 返回val
            return keyToVal.get(key);
        }

        public void put(int key, int value) {
            if (cap <= 0)
                return;
            // 若key已存在
            if (keyToVal.containsKey(key)) {
                // 修改对应的val
                keyToVal.put(key, value);
                // key对应的freq+1
                increaseFreq(key);
                return;
            }
            // 若key不存在,则新增需要判断容量
            // 若容量已满,需要删除freq最小的key
            if (cap == keyToVal.size())
                removeMinFreqKey();
            // 插入KV，将key对应的freq设置为1
            // 插入KV表
            keyToVal.put(key, value);
            // 插入KF表
            keyToFreq.put(key, 1);
            // 插入FK表
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            freqToKeys.get(1).add(key);
            // 插入新Key后的最小freq是1
            minFreq = 1;
        }

        /**
         * 增加key对应的freq
         */
        private void increaseFreq(int key) {
            // 1. 修改KF表
            Integer freq = keyToFreq.get(key);
            keyToFreq.put(key, freq + 1);
            // 2. 修改FK表
            // 2.1. 删除freq对应的keys中的key
            freqToKeys.get(freq).remove(key);
            // 2.2. 若删除key后keys为空，则删除该freq
            if (freqToKeys.get(freq).isEmpty()) {
                freqToKeys.remove(freq);
                // 2.2.1. 如果这个freq恰好是minFreq,则更新minFreq
                if (minFreq == freq)
                    minFreq++;
            }
            // 2.3. freq+1对应的keys中添加key
            freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
            freqToKeys.get(freq + 1).add(key);
        }

        /**
         * 删除freq最小的key
         */
        private void removeMinFreqKey() {
            // 1. 获取minFreq对应的Key
            LinkedHashSet<Integer> keys = freqToKeys.get(minFreq);
            // 1.1. 最先被插入的key就是需要被淘汰的key
            Integer key = keys.iterator().next();
            // 2. 修改KV表
            keyToVal.remove(key);
            // 3. 修改KF表
            keyToFreq.remove(key);
            // 4. 修改FK表
            keys.remove(key);
            if (keys.isEmpty())
                freqToKeys.remove(minFreq);
            /*
            * 为什么在FK中删除minFreq不需要更新minFreq？
            * 1. 时间复杂度高，没办法通过O(1)的时间复杂度更新minFreq，只能遍历FK表得到minFreq
            * 2. 没有必要，removeMinFreqKey方法只可能在put时被调用，put后一定会更新minFreq为1
            * */
        }
    }

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        LFUCache solution = new LfuCache().new LFUCache(2);
        // put your test code here

    }
}