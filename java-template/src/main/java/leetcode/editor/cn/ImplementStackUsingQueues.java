package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ImplementStackUsingQueues {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路一：用两个队列模拟栈
     * 1. 定义两个队列：队列1，队列2
     * 2. 判空操作：队列1和队列2均空
     * 3. 入栈操作：
     * 3.1. 若队列1和队列2都为空，则加入队列1
     * 3.2. 否则加入非空的队列
     * 4. 出栈操作：
     * 4.1. 找到非空队列，将队列中的元素出队到另一个队列，最后一个元素不入队，并返回
     * 5. 获取栈顶元素操作：
     * 5.1. 找到非空队列，将队列中的元素出队到另一个队列，返回最后一个元素
     */
//    class MyStack {
//        private final Queue<Integer> queue1;
//        public final Queue<Integer> queue2;
//
//        public MyStack() {
//            queue1 = new LinkedList<>();
//            queue2 = new LinkedList<>();
//        }
//
//        public void push(int x) {
//            // 判断队列2是否为空
//            if (queue2.isEmpty())
//                // 不管队列1是否为空都加入队列1
//                queue1.offer(x);
//            else
//                // 队列2不为空,则加入队列2
//                queue2.offer(x);
//        }
//
//        public int pop() {
//            // 找到非空队列
//            if (queue2.isEmpty()) {
//                // 除了最后一个元素,其余全部出队,入队到另一个队列
//                while (queue1.size() > 1)
//                    queue2.offer(queue1.remove());
//                // 删除并返回最后一个元素
//                return queue1.poll();
//            } else {
//                while (queue2.size() > 1)
//                    queue1.offer(queue2.remove());
//                return queue2.poll();
//            }
//        }
//
//        public int top() {
//            // 找到非空队列
//            if (queue2.isEmpty()) {
//                // 除了最后一个元素,其余全部出队,入队到另一个队列
//                while (queue1.size() > 1)
//                    queue2.offer(queue1.remove());
//                // 记录最后一个元素
//                Integer poll = queue1.poll();
//                // 入队
//                queue2.offer(poll);
//                // 返回最后一个元素
//                return poll;
//            } else {
//                while (queue2.size() > 1)
//                    queue1.offer(queue2.remove());
//                Integer poll = queue2.poll();
//                queue1.offer(poll);
//                return poll;
//            }
//        }
//
//        public boolean empty() {
//            return queue1.isEmpty() && queue2.isEmpty();
//        }
//    }

    /**
     * 思路二：用一个队列模拟栈
     * 1. 定义队列
     * 2. 判空操作：队列为空
     * 3. 入栈操作：入队
     * 4. 出栈操作：
     * 4.1. 记录当前队列元素个数size
     * 4.2. 将元素出队再入队size - 1次
     * 4.3. 返回出队的元素
     * 5. 获取栈顶元素操作：
     * 5.1. 记录当前队列元素个数size
     * 5.2. 将元素出队再入队size次
     * 5.3. 记录并返回最后一个出队的元素
     */
//    class MyStack {
//        private final Queue<Integer> queue;
//
//        public MyStack() {
//            queue = new LinkedList<>();
//        }
//
//        public void push(int x) {
//            queue.offer(x);
//        }
//
//        public int pop() {
//            int size = queue.size();
//            for (int i = 0; i < size - 1; i++)
//                queue.offer(queue.poll());
//            return queue.poll();
//        }
//
//        public int top() {
//            int size = queue.size();
//            for (int i = 0; i < size - 1; i++)
//                queue.offer(queue.poll());
//            Integer poll = queue.poll();
//            queue.offer(poll);
//            return poll;
//        }
//
//        public boolean empty() {
//            return queue.isEmpty();
//        }
//    }

    /**
     * 思路三：用一个队列模拟栈
     * 1. 定义队列和栈顶元素变量
     * 2. 判空操作：队列为空
     * 3. 入栈操作：入队，并更新栈顶元素变量
     * 4. 出栈操作：
     * 4.1. 记录当前队列元素个数size
     * 4.2. 将元素出队再入队size - 1次
     * 4.3. 记录第size - 1个元素设为栈顶元素变量
     * 4.4. 返回第size个出队的元素
     * 5. 获取栈顶元素操作：返回栈顶元素变量
     */
    class MyStack {
        private final Queue<Integer> queue;
        private int top;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            top = x;
        }

        public int pop() {
            int size = queue.size();
            for (int i = 0; i < size - 2; i++)
                queue.offer(queue.poll());
            // top为新队尾
            top = queue.peek();
            queue.offer(queue.poll());
            // 返回队尾
            return queue.poll();
        }

        public int top() {
            return top;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        MyStack solution = new ImplementStackUsingQueues().new MyStack();
        // put your test code here

    }
}