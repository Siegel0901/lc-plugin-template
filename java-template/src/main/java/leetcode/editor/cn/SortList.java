package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortList {

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
    /*
     * 思路四：归并排序（自顶向下，递归实现）
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(nlogn)
     * */
    class Solution {
        public ListNode sortList(ListNode head) {
            // base case
            if (head == null || head.next == null)
                return head;
            // 获取中间节点
            ListNode head2 = getMidNode(head);
            // 分治
            head = sortList(head);
            head2 = sortList(head2);
            // 合并
            return merge(head, head2);
        }

        /*
         * 快慢指针获取链表的中间节点
         * prev指向左半部分尾节点，slow指向右半部分头节点
         * 偶数时，prev指向左中位数，slow指向右中位数
         * */
        ListNode getMidNode(ListNode head) {
            ListNode prev = head;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                // pre记录slow的前一个节点
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            // 断开slow前一个节点与slow的连接
            prev.next = null;
            return slow;
        }

        /**
         * 合并两个有序链表
         *
         * @param l1 有序链表1
         * @param l2 有序链表2
         * @return 返回合并链表的头节点
         */
        ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(Integer.MIN_VALUE);
            ListNode tail = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    tail.next = l1;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    l2 = l2.next;
                }
                tail = tail.next;
            }
            tail.next = (l1 != null) ? l1 : l2;
            return dummy.next;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路三：归并排序（自底向上，迭代实现）
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * */
    class Solution3 {
        public ListNode sortList(ListNode head) {
            // 计算链表长度
            int length = getListLength(head);
            // 声明虚拟头节点
            ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
            // step表示当前合并的子链表长度
            for (int step = 1; step < length; step <<= 1) {
                // prev指向未排序链表首节点的前驱
                ListNode prev = dummy;
                // curr指向未排序链表首节点
                ListNode curr = dummy.next;

                // 将链表拆分为多个长度为subLength的段，两两合并
                while (curr != null) {
                    // 第一个子链表的头节点
                    ListNode head1 = curr;
                    // 分割第一个子链表，同时获取第二个子链表的头节点
                    ListNode head2 = splitList(head1, step);
                    // 分割第二个子链表，同时curr指向未排序链表首节点
                    curr = splitList(head2, step);

                    // 合并两个有序子链表
                    ListNode[] merged = merge(head1, head2);

                    // perv的后继为合并链表的头节点
                    prev.next = merged[0];
                    // 移动prev到合并后链表的末尾,即未排序链表首节点的前驱
                    prev = merged[1];
                }
            }
            return dummy.next;
        }

        /**
         * 分割链表
         *
         * @param head 分割链表的头节点
         * @param size 分割子链表的长度
         * @return 返回分割后剩余链表的头节点。 <p>
         * 链表长度小于等于size，返回空节点； <p>
         * 链表长度大于size，分割size大小的子链表，返回剩余链表的头节点。
         */
        ListNode splitList(ListNode head, int size) {
            ListNode curr = head;
            // size链表大小需要走size-1步
            for (int i = 1; i < size && curr != null; i++)
                curr = curr.next;
            /*
             * curr == null 表明链表长度等于size
             * curr.next == null 表明链表长度小于size
             * 链表长度小于等于size都需要返回空节点,表明当前链表就是分割链表,剩余链表为空
             * */
            if (curr == null || curr.next == null)
                return null;
            // 链表长度大于size,获取剩余链表头节点
            ListNode nextHead = curr.next;
            // 断开分割链表
            curr.next = null;
            // 返回剩余链表头节点
            return nextHead;
        }

        // 获取链表长度
        int getListLength(ListNode head) {
            int length = 0;
            ListNode p = head;
            while (p != null) {
                length++;
                p = p.next;
            }
            return length;
        }

        /**
         * 合并两个有序链表
         *
         * @param l1 有序链表1
         * @param l2 有序链表2
         * @return 返回合并链表的头节点和尾节点
         */
        ListNode[] merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(Integer.MIN_VALUE);
            ListNode tail = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    tail.next = l1;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    l2 = l2.next;
                }
                tail = tail.next;
            }
            tail.next = (l1 != null) ? l1 : l2;
            while (tail.next != null)
                tail = tail.next;
            return new ListNode[]{dummy.next, tail};
        }
    }

    /*
     * 思路二：插入排序
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 【Time Limit Exceeded】
     * */
    class Solution2 {
        public ListNode sortList(ListNode head) {
            ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
            ListNode pPrev = dummy;
            ListNode p = head;
            while (p != null) {
                ListNode qPrev = dummy;
                ListNode q = dummy.next;
                // q不断后移，直至q移到p位置或移动到插入位置的前驱
                while (q != null && q != p && q.val <= p.val) {
                    q = q.next;
                    qPrev = qPrev.next;
                }
                // q.next不等于p时，说明q移动到p插入位置的前驱
                if (q != null && q != p) {
                    // 记录p当前节点
                    ListNode cur = p;
                    // p后移
                    p = p.next;
                    // 删除cur节点
                    pPrev.next = p;
                    // 插入cur到q位置
                    cur.next = q;
                    qPrev.next = cur;
                } else {
                    // p和pPrev后移
                    pPrev = p;
                    p = p.next;
                }
            }
            return dummy.next;
        }
    }

    /*
     * 思路一：排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        public ListNode sortList(ListNode head) {
            List<Integer> nums = new ArrayList<>();
            ListNode p = head;
            while (p != null) {
                nums.add(p.val);
                p = p.next;
            }
            Collections.sort(nums);
            p = head;
            for (int num : nums) {
                p.val = num;
                p = p.next;
            }
            return head;
        }
    }


    public static void main(String[] args) {
        Solution solution = new SortList().new Solution();
        // put your test code here
        solution.sortList(ListNode.createHead(new int[]{4, 2, 1, 3}));
    }
}