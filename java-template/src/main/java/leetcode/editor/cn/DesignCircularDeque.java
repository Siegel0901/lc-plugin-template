package leetcode.editor.cn;

public class DesignCircularDeque {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：环形数组实现循环队列
     * 1. 头尾指针front和rear初值为0
     * 1.1. front表示队首元素所在位置
     * 1.2. rear指向队尾元素的下一个位置
     * 2. 判空：rear == front，即要插入元素的位置为队首位置
     * 3. 判满：插入位置(rear)的下一个位置是否为front，是则代表满了，不插入（牺牲一个空间）
     * 3.1. 因此容量为k的队列需要k+1的数组
     * 4. len表示数组的长度，为k+1
     * 4.1. 后移操作为 (index + 1) % len
     * 4.2. front和rear前移的时候，需要判断是否为0，若为0，则上一个位置是len - 1
     * 所有操作时间复杂度：O(1)
     * 空间复杂度：O(k)
     */
    class MyCircularDeque {
        private final int[] q;
        private int front = 0;
        private int rear = 0;
        private final int len;

        public MyCircularDeque(int k) {
            len = k + 1;
            q = new int[len];
        }

        public boolean insertFront(int value) {
            if (isFull())
                return false;
            front = front == 0 ? len - 1 : (front - 1) % len;
            q[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull())
                return false;
            q[rear] = value;
            rear = (rear + 1) % len;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty())
                return false;
            front = (front + 1) % len;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty())
                return false;
            rear = rear == 0 ? len - 1 : (rear - 1) % len;
            return true;
        }

        public int getFront() {
            if (isEmpty())
                return -1;
            return q[front];
        }

        public int getRear() {
            if (isEmpty())
                return -1;
            return q[rear == 0 ? len - 1 : (rear - 1) % len];
        }

        public boolean isEmpty() {
            return rear == front;
        }

        public boolean isFull() {
            return (rear + 1) % len == front;
        }
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        MyCircularDeque solution = new DesignCircularDeque().new MyCircularDeque(3);
        // put your test code here

    }
}