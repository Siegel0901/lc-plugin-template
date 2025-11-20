package leetcode.editor.cn;

public class DesignLinkedList {

    //leetcode submit region begin(Prohibit modification and deletion)
/*
    // 动态数组代码实现
    class MyLinkedList {
        private int[] data;
        private int size;
        private static final int INIT_CAP = 1;

        private void resize(int newCap) {
            int[] newArr = new int[newCap];
            if (size >= 0) System.arraycopy(data, 0, newArr, 0, size);
            data = newArr;
        }

        public MyLinkedList() {
            this(INIT_CAP);
        }

        public MyLinkedList(int initCapacity) {
            data = new int[initCapacity];
            size = 0;
        }

        public int get(int index) {
            if (checkDeleteIndex(index))
                return -1;
            return data[index];
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            if (size == data.length) {
                resize(size * 2);
            }
            data[size] = val;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (checkAddIndex(index))
                return;
            if (size == data.length) {
                resize(size * 2);
            }
            for (int i = size; i >= index + 1; i--) {
                data[i] = data[i - 1];
            }
            data[index] = val;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (checkDeleteIndex(index))
                return;
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            size--;
            if (size == data.length / 4) {
                resize(data.length / 2);
            }
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private boolean checkDeleteIndex(int index) {
            return index < 0 || index >= size;
        }

        private boolean checkAddIndex(int index) {
            return index < 0 || index > size;
        }

    }
*/
/*    // 单链表代码实现(带头尾结点)
    class MyLinkedList {
        public static class Node {
            private Integer val;
            private Node next;

            Node() {
                this(null);
            }

            Node(Integer val) {
                this.val = val;
                this.next = null;
            }
        }

        private Node dummyHead;
        private Node dummyTail;
        private int size;

        public MyLinkedList() {
            this.dummyHead = new Node();
            this.dummyTail = new Node();
            this.dummyHead.next = dummyTail;
            this.size = 0;
        }

        private Node insert(Node prev, int val) {
            Node next = prev.next;
            Node node = new Node(val);
            node.next = next;
            prev.next = node;
            return node;
        }

        private Node del(Node prev) {
            Node d = prev.next;
            prev.next = d.next;
            return d;
        }

        public int get(int index) {
            if (checkDeleteIndex(index))
                return -1;
            Node prevNode = getPrevNode(index);
            return prevNode.next.val;
        }

        public void addAtHead(int val) {
            insert(dummyHead, val);
            size++;
        }

        public void addAtTail(int val) {
            Node p = dummyHead;
            while (p.next != dummyTail) {
                p = p.next;
            }
            insert(p, val);
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (checkAddIndex(index))
                return;
            insert(getPrevNode(index), val);
            size++;
        }

        public void deleteAtIndex(int index) {
            if (checkDeleteIndex(index))
                return;
            del(getPrevNode(index));
            size--;
        }

        public void deleteAtHead() {
            if (isEmpty()) return;
            del(dummyHead);
            size--;
        }

        public void deleteAtTail() {
            if (isEmpty()) return;
            Node prev = dummyHead;
            while (prev.next != dummyTail) {
                prev = prev.next;
            }
            del(prev);
            size--;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private Node getPrevNode(int index) {
            Node p = dummyHead;
            int position = -1;
            while (p.next != dummyTail) {
                if (position == index - 1)
                    break;
                p = p.next;
                position++;
            }
            return p;
        }

        private boolean checkDeleteIndex(int index) {
            return index < 0 || index >= size;
        }

        private boolean checkAddIndex(int index) {
            return index < 0 || index > size;
        }

    }*/
    // 双链表代码实现(带头尾结点)
    class MyLinkedList {
        public static class Node {
            private Integer val;
            private Node next;
            private Node prev;

            Node() {
                this(null);
            }

            Node(Integer val) {
                this.val = val;
                this.next = null;
                this.prev = null;
            }
        }

        private Node dummyHead;
        private Node dummyTail;
        private int size;

        public MyLinkedList() {
            this.dummyHead = new Node();
            this.dummyTail = new Node();
            this.dummyHead.next = dummyTail;
            this.dummyTail.prev = dummyHead;
            this.size = 0;
        }

        private Node insert(Node prev, int val) {
            Node next = prev.next;
            Node node = new Node(val);
            node.next = next;
            next.prev = node;
            node.prev = prev;
            prev.next = node;
            return node;
        }

        private Node del(Node prev) {
            Node d = prev.next;
            Node next = d.next;
            prev.next = next;
            next.prev = prev;
            return d;
        }

        public int get(int index) {
            if (checkDeleteIndex(index)) return -1;
            Node prev = getPrevNode(index);
            return prev.next.val;
        }

        public void addAtHead(int val) {
            insert(dummyHead, val);
            size++;
        }

        public void addAtTail(int val) {
            insert(dummyTail.prev, val);
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (checkAddIndex(index)) return;
            insert(getPrevNode(index), val);
            size++;
        }

        public void deleteAtIndex(int index) {
            if (checkDeleteIndex(index)) return;
            del(getPrevNode(index));
            size--;
        }

        public void deleteAtHead(){
            if (isEmpty()) return;
            del(dummyHead);
            size--;
        }

        public void deleteAtTail(){
            if (isEmpty()) return;
            del(dummyTail.prev);
            size--;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private Node getPrevNode(int index) {
            Node p = dummyHead;
            int position = -1;
            while (p.next != dummyTail) {
                if (position == index - 1) break;
                p = p.next;
                position++;
            }
            return p;
        }

        private boolean checkDeleteIndex(int index) {
            return index < 0 || index >= size;
        }

        private boolean checkAddIndex(int index) {
            return index < 0 || index > size;
        }

    }


    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        // MyLinkedList obj = new DesignLinkedList().new MyLinkedList();
        // put your test code here
    }
}