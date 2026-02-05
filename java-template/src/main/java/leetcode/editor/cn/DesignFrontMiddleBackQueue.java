package leetcode.editor.cn;

import java.util.ArrayDeque;

public class DesignFrontMiddleBackQueue {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路一：带头尾指针链表实现
     * 1. 头指针head用于头插和删除,尾指针tail用于尾插
     * 2. 中间插入删除则用双指针找到链表中间节点，链表节点个数为偶数时，中间节点为靠前的节点
     * 3. 中间插入需要判断链表节点的奇偶，奇数则在中间节点前插入，偶数则在中间节点后插入
     * 非中间操作时间复杂度：O(1)
     * 中间操作时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
//    class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int val) {
//            this(val, null);
//        }
//
//        ListNode(int val, ListNode next) {
//            this.val = val;
//            this.next = next;
//        }
//
//        public ListNode createHead(int[] nums) {
//            if (nums == null || nums.length == 0) {
//                return null;
//            }
//            ListNode head = new ListNode(nums[0]);
//            ListNode cur = head;
//            for (int i = 1; i < nums.length; i++) {
//                cur.next = new ListNode(nums[i]);
//                cur = cur.next;
//            }
//            return head;
//        }
//    }
//
//    class FrontMiddleBackQueue {
//
//        private final ListNode dummy;
//        private ListNode tail;
//        private int size;
//
//        private ListNode insert(ListNode pre, int value) {
//            ListNode next = pre.next;
//            ListNode p = new ListNode(value, next);
//            pre.next = p;
//            return p;
//        }
//
//        private int del(ListNode dummy, ListNode node) {
//            ListNode next = node.next;
//            ListNode pre = findPre(dummy, node);
//            pre.next = next;
//            return node.val;
//        }
//
//        private ListNode findPre(ListNode dummy, ListNode node) {
//            ListNode pre = dummy;
//            ListNode p = dummy.next;
//            while (p != node) {
//                p = p.next;
//                pre = pre.next;
//            }
//            return pre;
//        }
//
//        private ListNode getMid(ListNode dummy) {
//            ListNode pre = dummy;
//            ListNode fast = dummy.next;
//            ListNode slow = dummy.next;
//            while (fast != null && fast.next != null) {
//                pre = pre.next;
//                slow = slow.next;
//                fast = fast.next.next;
//            }
//            if (fast == null)
//                return pre;
//            return slow;
//        }
//
//        public FrontMiddleBackQueue(ListNode head) {
//            dummy = new ListNode(0, head);
//            tail = dummy;
//            while (tail.next != null) {
//                size++;
//                tail = tail.next;
//            }
//        }
//
//        public FrontMiddleBackQueue() {
//            dummy = new ListNode(0);
//            tail = dummy;
//            size = 0;
//        }
//
//        public void pushFront(int val) {
//            if (size == 0) {
//                pushBack(val);
//                return;
//            }
//            insert(dummy, val);
//            size++;
//        }
//
//        public void pushMiddle(int val) {
//            if (size == 0) {
//                pushBack(val);
//                return;
//            }
//            ListNode mid = getMid(dummy);
//            ListNode pre = findPre(dummy, mid);
//            if (size % 2 == 0)
//                /*
//                 * 链表节点为偶数时，需要在中间节点后插入
//                 * [1,2] -> [1,3,2]
//                 * */
//                insert(mid, val);
//            else
//                /*
//                 * 链表节点为奇数时，需要在中间节点的前驱后插入
//                 * [1,3,2] -> [1,4,3,2]
//                 * */
//                insert(pre, val);
//            size++;
//        }
//
//        public void pushBack(int val) {
//            tail = insert(tail, val);
//            size++;
//        }
//
//        public int popFront() {
//            if (size == 0)
//                return -1;
//            if (size == 1)
//                return popBack();
//            size--;
//            return del(dummy, dummy.next);
//        }
//
//        public int popMiddle() {
//            if (size == 0)
//                return -1;
//            if (size == 1)
//                return popBack();
//            size--;
//            ListNode mid = getMid(dummy);
//            return del(dummy, mid);
//        }
//
//        public int popBack() {
//            if (size == 0)
//                return -1;
//            ListNode pre = findPre(dummy, tail);
//            int val = tail.val;
//            pre.next = null;
//            tail = pre;
//            size--;
//            return val;
//        }

    /**
     * 思路二：两个双端队列实现
     * 1. 用left和right两个双端队列维护前中后队列
     * 2. 需要保证right的元素个数为left的元素个数或left的元素个数+1
     * 2.1. 这样当元素个数为偶数时，[1],[2]，插入中间元素在right头部，[1],[3,2]
     * 2.2. 这样当元素个数为奇数时，[1],[3,2]，插入中间元素在left尾部，[1,4],[3,2]
     * 2.3. 满足题意：插在中间或中间元素前面
     * 5. 当元素个数为奇数时，popMiddle在right头部
     * 6. 当元素个数为偶数时，popMiddle在left尾部
     * 7. 当元素个数为1时，popBack在right头部
     * 8. 每次操作需要检查left和right的元素个数，保证right.size∈[left.size, left.size + 1]
     * 所有操作时间复杂度：O(1)
     * 空间复杂度：O(n)
     */
    class FrontMiddleBackQueue {
        ArrayDeque<Integer> left;
        ArrayDeque<Integer> right;

        public FrontMiddleBackQueue() {
            left = new ArrayDeque<>();
            right = new ArrayDeque<>();
        }

        /**
         * 用于维持left.size ∈ [right.size, right.size + 1]
         */
        public void balance() {
            // 左边多,右边少
            if (right.size() < left.size())
                // 左边尾部给右边头部
                right.addFirst(left.removeLast());
            // 左边少,右边多
            if (right.size() > left.size() + 1)
                // 右边头部给左边尾部
                left.addLast(right.removeFirst());
        }

        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }

        public void pushMiddle(int val) {
            if (size() % 2 == 0)
                right.addFirst(val);
            else
                left.addLast(val);
            balance();
        }

        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }

        public int popFront() {
            if (size() == 0)
                return -1;
            if (size() == 1)
                return right.removeFirst();
            Integer val = left.removeFirst();
            balance();
            return val;
        }

        public int popMiddle() {
            if (size() == 0)
                return -1;
            int val;
            if (size() % 2 == 0)
                val = left.removeLast();
            else
                val = right.removeFirst();
            balance();
            return val;
        }

        public int popBack() {
            if (size() == 0)
                return -1;
            int val = right.removeLast();
            balance();
            return val;
        }

        public int size() {
            return left.size() + right.size();
        }
    }

    /**
     * Your FrontMiddleBackQueue object will be instantiated and called as such:
     * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
     * obj.pushFront(val);
     * obj.pushMiddle(val);
     * obj.pushBack(val);
     * int param_4 = obj.popFront();
     * int param_5 = obj.popMiddle();
     * int param_6 = obj.popBack();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        FrontMiddleBackQueue solution = new DesignFrontMiddleBackQueue().new FrontMiddleBackQueue();
        // put your test code here
        solution.pushFront(1);
        solution.pushBack(2);
        solution.pushMiddle(3);
        solution.pushMiddle(4);
        solution.popFront();
        solution.popMiddle();
        solution.popMiddle();
        solution.popBack();
        solution.popFront();
//        ListNode dummy = new DesignFrontMiddleBackQueue().new ListNode(0);
//        dummy.next = dummy.createHead(new int[]{1, 2});
//        FrontMiddleBackQueue solution = new DesignFrontMiddleBackQueue().new FrontMiddleBackQueue(dummy.next);
//        solution.pushMiddle(3);
    }
}