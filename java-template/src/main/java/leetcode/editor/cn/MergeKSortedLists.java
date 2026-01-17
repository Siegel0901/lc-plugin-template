package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

import javax.print.DocFlavor;

public class MergeKSortedLists {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /**
         * 思路一：转为数组排序
         * 1. 将所有的链表节点的值存入数组
         * 2. 对数组进行排序
         * 3. 将数组转为链表
         * 时间复杂度：O(nlogn),n为节点数
         * 空间复杂度：O(n)
         *
         * @param lists 链表数组
         * @return 新链表
         */
//        public ListNode mergeKLists(ListNode[] lists) {
//            List<Integer> values = new ArrayList<>();
//
//            for (ListNode list : lists)
//                for (ListNode p = list; p != null; p = p.next)
//                    values.add(p.val);
//
//            Collections.sort(values);
//
//            Iterator<Integer> it = values.iterator();
//            ListNode dummy = new ListNode(0), p = dummy;
//            while (it.hasNext()) {
//                p.next = new ListNode(it.next());
//                p = p.next;
//            }
//            return dummy.next;
//        }

        /**
         * 思路二：小顶堆
         * 1. 将k个链表的头节点加入小顶堆
         * 2. 取出小顶堆的根节点（某个链表的头节点），加入新链表，调整小顶堆
         * 3. 步骤2中根节点对应链表的头节点后移，加入小顶堆，重复步骤2，直至堆为空
         * 时间复杂度：
         * O(nlogk),n为节点总数,k为链表数
         * 堆poll和add操作 -> O(logk)
         * 堆调整 -> O(2nlogk),n次poll + n次add = 最多2N次堆调整
         * 空间复杂度：O(k)
         *
         * @param lists 链表数组
         * @return 新链表
         */
//        public ListNode mergeKLists(ListNode[] lists) {
//            // 构造方法中的capacity至少为1
//            if (lists.length == 0)
//                return null;
//
//            // 建堆大小指定为lists.length
//            Queue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
//
//            for (ListNode node : lists)
//                // 针对[[]]等空链表情况
//                if (node != null)
//                    queue.add(node);
//
//            // 虚拟头节点dummy和工作指针p
//            ListNode dummy = new ListNode(0), p = dummy;
//            while (!queue.isEmpty()) {
//                // 取出根节点(val最小的节点)
//                ListNode poll = queue.poll();
//                // 连接到新链表
//                p.next = poll;
//                // 若该链表还有后续节点,则加入小顶堆
//                if (poll.next != null)
//                    queue.add(poll.next);
//                // 工作指针后移,指向新链表的尾部,方便插入
//                p = p.next;
//                // 断开与原链表的链接(可选)
//                p.next = null;
//            }
//            // 返回新链表(去掉虚拟头节点)
//            return dummy.next;
//        }

        /**
         * 思路三：分治法(归并排序)
         * 1. 链表之间两两合并,最终合并为新链表
         * 时间复杂度:O(nlogk)
         * 合并操作时间复杂度:O(n)
         * 合并次数:logk
         * 空间复杂度:O(logk),递归深度
         *
         * @param lists 链表数组
         * @return 新链表
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) return null;
            return mergeLists(lists, 0, lists.length - 1);
        }

        // 合并lists数组的[start,end]区间
        public ListNode mergeLists(ListNode[] lists, int start, int end) {
            // 递归出口：区间内仅有1条链表，返回该链表
            if (start == end) return lists[start];
            // 2分区间
            int mid = start + (end - start) / 2;
            // 得到左区间合并后的结果
            ListNode left = mergeLists(lists, start, mid);
            // 得到右区间合并后的结果
            ListNode right = mergeLists(lists, mid + 1, end);
            // 合并左右区间
            return mergeTwoSortedLists(left, right);
        }

        public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1), p = dummy;
            ListNode p1 = l1, p2 = l2;
            while (p1 != null && p2 != null) {
                if (p1.val <= p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            p.next = p1 != null ? p1 : p2;
            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
        // put your test code here
        ListNode l1 = ListNode.createHead(new int[]{1, 4, 5});
        ListNode l2 = ListNode.createHead(new int[]{1, 3, 4});
        ListNode l3 = ListNode.createHead(new int[]{2, 6});
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        ListNode mergedKLists = solution.mergeKLists(lists);
        ListNode.print(mergedKLists);
    }
}