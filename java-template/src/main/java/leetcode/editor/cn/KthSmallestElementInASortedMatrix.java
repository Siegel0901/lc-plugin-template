package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class KthSmallestElementInASortedMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：大顶堆
         * 1. 初始化一个容量为k的大顶堆
         * 2. 遍历数组，元素加入大顶堆
         * 3. 大顶堆的根节点即为第k小的元素
         * 时间复杂度：O(n^2logk)
         * 空间复杂度：O(k)
         *
         * @param matrix 矩阵
         * @param k      第k小的元素
         * @return 第k小的元素
         */
//        public int kthSmallest(int[][] matrix, int k) {
//            // 创建一个初始容量为k的大顶堆
//            Queue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
//            // 遍历数组
//            for (int[] row : matrix) {
//                for (int num : row) {
//                    // 如果大顶堆容量小于k，则加入大顶堆
//                    if (maxHeap.size() < k)
//                        maxHeap.offer(num);
//                    // 如果大顶堆容量等于k，则比较当前元素和堆顶元素
//                    // 如果当前元素小于堆顶元素，则将堆顶元素弹出，当前元素加入大顶堆
//                    else if (num < maxHeap.peek()) {
//                        maxHeap.poll();
//                        maxHeap.offer(num);
//                    }
//                }
//            }
//            return maxHeap.peek();
//        }

        /**
         * 思路二：小顶堆合并有序链表思想
         * 1. 由于矩阵的每行和每列元素均按升序排序，故可以看作是n个有序链表
         * 2. 初始化容量为n的小顶堆
         * 3. 将每行的首元素加入小顶堆
         * 4. 堆不为空时，弹出堆顶元素，加入该元素所在数组的后一个元素
         * 5. 第k次弹出的堆顶元素即为第k小的元素
         * 时间复杂度：O(n+klogn)
         * 空间复杂度：O(n)
         *
         * @param matrix 矩阵
         * @param k      第k小的元素
         * @return 第k小的元素
         */
        public int kthSmallest(int[][] matrix, int k) {
            // 创建一个初始容量为n的小顶堆
            Queue<List<Integer>> minHeap = new PriorityQueue<>(matrix.length, Comparator.comparingInt(o -> o.get(0)));
            // 将每行的首元素加入小顶堆
            for (int i = 0; i < matrix.length; i++) {
                // 创建一个链表，保存当前元素以及其所在行和列索引
                List<Integer> list = new ArrayList<>(3);
                list.add(matrix[i][0]);
                list.add(i);
                list.add(0);
                // 将该链表加入小顶堆
                minHeap.offer(list);
            }

            int res = Integer.MIN_VALUE;
            // 堆不为空时，弹出堆顶元素，加入该元素所在数组的后一个元素
            while (!minHeap.isEmpty() && k > 0) {
                // 弹出堆顶元素
                List<Integer> list = minHeap.poll();
                // 获取堆顶元素
                res = list.get(0);
                k--;
                // 如果该元素所在行有下一个元素，则加入小顶堆
                int i = list.get(1);
                int j = list.get(2);
                if (j + 1 < matrix.length) {
                    List<Integer> next = new ArrayList<>(3);
                    next.add(matrix[i][j + 1]);
                    next.add(i);
                    next.add(j + 1);
                    minHeap.offer(next);
                }
            }

            // 返回第k小的元素
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new KthSmallestElementInASortedMatrix().new Solution();
        // put your test code here
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int i = solution.kthSmallest(matrix, 8);
        System.out.println(i);
    }
}