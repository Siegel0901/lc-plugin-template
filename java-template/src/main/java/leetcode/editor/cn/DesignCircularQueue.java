package leetcode.editor.cn;

public class DesignCircularQueue {

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
     * 所有操作时间复杂度：O(1)
     * 空间复杂度：O(k)
     */
    class MyCircularQueue {
        // 数组q存储队列元素
        private final int[] q;
        // front为头指针，初值为0
        private int front = 0;
        // rear为尾指针,用于指向下一元素应该插入的位置,初值为0
        private int rear = 0;
        // len表示队列长度
        private final int len;

        public MyCircularQueue(int k) {
            len = k + 1;
            q = new int[len];
        }

        public boolean enQueue(int value) {
            if (isFull())
                return false;
            q[rear] = value;
            rear = (rear + 1) % len;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty())
                return false;
            front = (front + 1) % len;
            return true;
        }

        public int Front() {
            if (isEmpty())
                return -1;
            return q[front];
        }

        public int Rear() {
            if (isEmpty())
                return -1;
            return q[(rear - 1 + len) % len];
        }

        /**
         * 判断队列是否为空
         * rear表示下一个元素应该插入的位置
         * 如果rear==front表示下一个元素应该插入队首
         * 即队列为空
         *
         * @return true表示队列为空
         */
        public boolean isEmpty() {
            return rear == front;
        }

        /**
         * 判断队列是否为满
         * rear表示下一个元素应该插入的位置
         * 插入后rear需要后移
         * 当rear后移的位置是front时，表示队列满了
         * 此时不应该把rear处插入元素，即牺牲掉1个数组元素的空间
         *
         * @return true表示队列已满
         */
        public boolean isFull() {
            return (rear + 1) % len == front;
        }
    }

    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        MyCircularQueue solution = new DesignCircularQueue().new MyCircularQueue(3);
        // put your test code here
        solution.enQueue(1);
        solution.enQueue(1);
        solution.enQueue(1);
        solution.enQueue(1);
        solution.Rear();
        solution.isFull();
        solution.deQueue();
        solution.enQueue(4);
        solution.Rear();
    }
}