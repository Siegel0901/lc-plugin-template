package leetcode.editor.cn;

public class DesignLinkedList {

    //leetcode submit region begin(Prohibit modification and deletion)
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