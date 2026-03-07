package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleLinkedList {
        Node head;
        Node tail;

        int size;

        DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        /**
         * 在链表末尾插入节点
         *
         * @param node 要插入的节点
         */
        void addLast(Node node) {
            // 记录要插入位置的前驱后继
            Node prev = tail.prev;
            Node next = tail;
            // 设置node的前驱后继
            node.prev = prev;
            node.next = next;
            // node的前驱的后继改为node
            prev.next = node;
            // node的后继的前驱改为node
            next.prev = node;
            size++;
        }

        /**
         * 返回链表元素个数
         *
         * @return 链表元素个数
         */
        int size() {
            return size;
        }

        /**
         * 判断链表是否为空
         *
         * @return 链表是否为空
         */
        boolean isEmpty() {
            return size == 0;
        }

        /**
         * 删除节点:del一定存在
         *
         * @param del 待删除的节点
         */
        void remove(Node del) {
            // 记录要删除的节点的前驱后继
            Node prev = del.prev;
            Node next = del.next;
            // 前驱的后继改为要删除节点的后继
            prev.next = next;
            // 后继的前驱改为要删除节点的前驱
            next.prev = prev;
            size--;
        }

        /**
         * 删除首节点
         *
         * @return 返回首节点
         */
        Node removeFirst() {
            // 判空
            if (isEmpty())
                return null;
            // 记录首节点
            Node first = head.next;
            // 记录首节点的前驱后继
            Node prev = first.prev;
            Node next = first.next;
            // 前驱的后继为首节点后继
            prev.next = next;
            // 后继的前驱为首节点前驱
            next.prev = prev;
            size--;
            return first;
        }

    }

    class LRUCache {
        Map<Integer, Node> map;
        DoubleLinkedList cache;
        int cap;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            cache = new DoubleLinkedList();
            cap = capacity;
        }

        /**
         * 将key对应的节点设置为最近访问
         *
         * @param key key
         */
        private void makeRecently(int key) {
            // 根据key从map中获取节点
            Node node = map.get(key);
            // 链表中删除该节点
            cache.remove(node);
            // 将该节点加入到链表末尾
            cache.addLast(node);
        }

        /**
         * 向缓存中添加元素
         *
         * @param key   key
         * @param value value
         */
        private void addRecently(int key, int value) {
            Node node = new Node(key, value);
            map.put(key, node);
            cache.addLast(node);
        }

        /**
         * 根据key删除节点
         *
         * @param key key
         */
        void deleteKey(int key) {
            Node node = map.remove(key);
            cache.remove(node);
        }

        /**
         * 删除最近最少访问节点
         */
        void removeLeastRecently() {
            Node node = cache.removeFirst();
            map.remove(node.key);
        }


        public int get(int key) {
            // 检查map中是否存在key
            if (!map.containsKey(key))
                return -1;
            // 设置为最近访问
            makeRecently(key);
            // 返回节点的value
            return map.get(key).value;
        }

        public void put(int key, int value) {
            // 1. 检查map中是否存在key
            if (map.containsKey(key)) {
                // 1.1. 根据key删除节点
                deleteKey(key);
                // 1.2. 新增最近使用节点
                addRecently(key,value);
                return;
            }
            // 2. 不存在
            // 2.1. 检查容量是否足够
            if (cap == cache.size)
                // 2.2. 容量不够需要删除最近最少使用的节点
                removeLeastRecently();
            // 2.3. 新增最近使用节点
            addRecently(key, value);
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        LRUCache solution = new LruCache().new LRUCache(2);
        // put your test code here

    }
}