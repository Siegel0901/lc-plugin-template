package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class RemoveDuplicatesFromSortedListIi {

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
         * 思路一：集合去重
         * 1. 遍历链表，将链表的值加入集合set1
         * 2. 若集合中已存在该值，则加入set2
         * 3. 求set1与set2的差集
         * 4. 将差集的值转换为链表
         * 时间复杂度：O(nlogn)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
//        public ListNode deleteDuplicates(ListNode head) {
//            Set<Integer> set1 = new TreeSet<>();
//            Set<Integer> set2 = new TreeSet<>();
//            while (head != null) {
//                if (set1.contains(head.val)) {
//                    set2.add(head.val);
//                } else {
//                    set1.add(head.val);
//                }
//                head = head.next;
//            }
//            set1.removeAll(set2);
//            Iterator<Integer> it = set1.iterator();
//            ListNode dummy = new ListNode(-1), tail = dummy;
//            while (it.hasNext()) {
//                tail.next = new ListNode(it.next());
//                tail = tail.next;
//            }
//            return dummy.next;
//        }

        /**
         * 思路二：双指针遍历重复区域删除
         * 1. 链表是升序排序的，则可以保证重复值节点一定是聚集在一个区间内
         * 2. 创建一个虚拟头结点dummy，将dummy的next指向head
         * 3. 创建两个指针prev和current，初始时prev指向虚拟头结点dummy，current指向head
         * 4. 对比current节点的值与current.next节点的值
         * 4.1. 若相等，则current节点的值重复，记录重复值为duplicateVal，将current节点后移，直到current节点的值与duplicateVal不相等，此时将prev的next指向current节点
         * 4.2. 若不相等，则将prev节点后移一位，current节点后移一位
         * 5. 当current节点为空时，遍历结束，返回dummy的next节点
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
//        public ListNode deleteDuplicates(ListNode head) {
//            // 创建虚拟头结点 dummy
//            ListNode dummy = new ListNode(-1, head);
//            // 创建两个指针 prev 和 current
//            ListNode prev = dummy, current = dummy.next;
//            // 遍历链表，直到 current 节点为空
//            while (current != null) {
//                // 若 current 节点的 next 节点不为空，判断当前节点和下一个节点的值是否相等
//                if (current.next != null && current.next.val == current.val) {
//                    // 获取重复值
//                    int duplicateVal = current.val;
//                    // 移动 current 节点，直到当前节点的值与重复值不相等
//                    while (current != null && current.val == duplicateVal)
//                        current = current.next;
//                    // 将 prev 节点的 next 指向 current 节点
//                    prev.next = current;
//                } else {    // 若当前节点和下一个节点的值不相等
//                    // prev 和 current 节点，各向后移动一位
//                    prev = prev.next;
//                    current = current.next;
//                }
//            }
//            return dummy.next;
//        }

        /**
         * 思路三：链表分解
         * 1. 遍历链表,将重复节点和非重复节点放入不同链表中
         * 2. 判重：
         * 2.1. 当前节点和后继节点的值相等 或者 当前节点和重复节点链表尾节点值相等，则当前节点加入重复节点链表
         * 2.2. 否则加入不重复节点链表
         * 3. 遍历完链表，返回节点不重复的链表
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
//        public ListNode deleteDuplicates(ListNode head) {
//            // 定义不重复节点链表
//            ListNode dummyUniq = new ListNode(101);
//            // 定义重复节点链表
//            ListNode dummyDup = new ListNode(101);
//            // 遍历链表指针
//            ListNode p = head;
//            // 不重复节点链表指针和重复节点链表指针
//            ListNode pUniq = dummyUniq, pDup = dummyDup;
//            // 遍历链表
//            while (p != null) {
//                // 判断当前节点和后继节点的值是否相等 或 当前节点和重复节点链表尾节点值是否相等
//                if (p.next != null && p.next.val == p.val || p.val == pDup.val) {
//                    // 将当前节点加入重复节点链表
//                    pDup.next = p;
//                    // 重复节点链表指针后移
//                    pDup = pDup.next;
//                } else {    // 当前节点和后继节点的值不相等 且 当前节点和重复节点链表尾节点值不相等
//                    // 将当前节点加入不重复节点链表
//                    pUniq.next = p;
//                    // 不重复节点链表指针后移
//                    pUniq = pUniq.next;
//                }
//                // 链表指针后移
//                p = p.next;
//                // 与原链表节点断开
//                pDup.next = null;
//                pUniq.next = null;
//            }
//            // 返回不重复节点链表的头结点
//            return dummyUniq.next;
//        }

        /**
         * 思路三：递归去重
         * 1. 当链表为空或者链表只有一个节点时，则去重完成，因此可以作为递归边界
         * 2. 如果头节点与后继节点不同，则对后继节点为头节点的链表进行去重，返回头节点
         * 3. 如果头节点与后继节点不同，则说明当前链表从头节点开始有重复区间，需要找到重复区间结束后的第一个节点为头节点进行去重
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
        public ListNode deleteDuplicates(ListNode head) {
            // 递归边界：链表为空或链表只有一个节点
            if (head == null || head.next == null)
                return head;
            // 链表至少有两个节点，如果当前节点与后继节点值相等
            if (head.val == head.next.val) {
                // 记录重复值
                int duplicatesVal = head.val;
                // 找到第一个值不为重复值的节点
                while (head != null && head.val == duplicatesVal) {
                    head = head.next;
                }
                // 返回以该节点为头节点进行去重后的结果
                return deleteDuplicates(head);
            } else {    // 当前节点与后继节点值不相等
                // 当前节点的后继更新为以当前节点后继为头节点去重后的链表
                head.next = deleteDuplicates(head.next);
                // 返回头节点
                return head;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedListIi().new Solution();
        // put your test code here

    }
}