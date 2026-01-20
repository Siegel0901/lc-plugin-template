package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class FindKPairsWithSmallestSums {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：大顶堆存前k小的数对
         * 1. 创建容量为k的大顶堆
         * 2. 遍历两个数组，将数对存到大顶堆
         * 3. 遍历结束时，大顶堆中为前k小的数对
         * 4. 使用头插法大顶堆弹出的堆顶数对插入到链表中
         * 5. 返回链表
         * 时间复杂度：O(m*n*logk + k) -> O(m*n*logk)，m和n为数组长度
         * 空间复杂度：O(k)
         * LC超时：数据量达到10^5，O(n^2)的算法极容易超时
         * 并没有利用到num1和nums2本身的非递减顺序
         *
         * @param nums1 数组1
         * @param nums2 数组2
         * @param k 数对个数
         * @return 前k小的数对
         */
//        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//            Queue<List<Integer>> maxHeap = new PriorityQueue<>(k, (a, b) -> b.get(0) + b.get(1) - a.get(0) - a.get(1));
//            for (int num1 : nums1) {
//                for (int num2 : nums2) {
//                    List<Integer> pair = new ArrayList<>(2);
//                    pair.add(num1);
//                    pair.add(num2);
//                    if (maxHeap.size() < k) {
//                        maxHeap.offer(pair);
//                    } else if (pair.get(0) + pair.get(1) < maxHeap.peek().get(0) + maxHeap.peek().get(1)) {
//                        maxHeap.poll();
//                        maxHeap.offer(pair);
//                    }
//                }
//            }
//            List<List<Integer>> res = new LinkedList<>();
//            while (!maxHeap.isEmpty()) {
//                List<Integer> list = maxHeap.poll();
//                res.add(0, list);
//            }
//            return res;
//        }

        /**
         * 思路二：小顶堆合并有序链表思想
         * 1. 由于num1与num2具有非递减的顺序，故数对序列可以看作是num1.length个非递减链表
         * 1.1. (num1[0], num2[0]) < (num1[0], num2[1]) < ... < (num1[0], num2[j])
         * 1.2. ...
         * 1.3. (num1[i], num2[0]) < (num1[i], num2[1]) < ... < (num1[i], num2[j])
         * 2. 提取每个链表数对节点的num1元素，剩下的num2元素就是升序链表，可以利用合并有序链表的思想取得前k个最小数对
         * 3. 创建一个初始容量为nums1.length的小顶堆
         * 4. 把 (num1[i], num2[0]) 加入小顶堆 (0 <= i < num1.length)
         * 5. 取出k次堆顶，每次取出都需要加入该数对对应的下一个数对
         * 时间复杂度：O(n + klogn)
         * 空间复杂度：O(n)
         * @param nums1 数组1
         * @param nums2 数组2
         * @param k     数对个数
         * @return 前k小的数对
         */
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            // 创建初始容量为nums1.length的小顶堆,存储三元组[num1[i], num2[j], j]
            Queue<List<Integer>> minHeap = new PriorityQueue<>(nums1.length, Comparator.comparingInt(a -> a.get(0) + a.get(1)));
            // 把 (num1[i], num2[0]) 加入小顶堆 (0 <= i < num1.length)
            for (int num : nums1) {
                List<Integer> node = new ArrayList<>(3);
                node.add(num);
                node.add(nums2[0]);
                node.add(0);
                minHeap.offer(node);
            }

            List<List<Integer>> res = new ArrayList<>();
            // 取出k次堆顶，每次取出都需要加入该数对对应的下一个数对
            while (!minHeap.isEmpty() && k > 0) {
                List<Integer> node = minHeap.poll();
                k--;

                // 收集堆顶数对
                List<Integer> pair = new ArrayList<>();
                pair.add(node.get(0));
                pair.add(node.get(1));
                res.add(pair);

                // 加入该数对对应的下一个数对
                int j = node.get(2);
                if (j + 1 < nums2.length) {
                    List<Integer> next = new ArrayList<>(3);
                    next.add(node.get(0));
                    next.add(nums2[j + 1]);
                    next.add(j + 1);
                    minHeap.offer(next);
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new FindKPairsWithSmallestSums().new Solution();
        // put your test code here

    }
}