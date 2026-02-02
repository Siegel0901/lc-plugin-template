package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class ImplementQueueUsingStacks {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：
     * 1. 定义两个栈，s1和s2
     * 2. 判空：s1和s2均不为空
     * 3. 入队：元素入栈s1
     * 4. 出队：
     * 4.1. 判断s2是否为空，若为空，则将s1中的元素全部出栈，并入栈s2
     * 4.2. 将s2的栈顶元素出栈
     * 5. 返回队列开头元素
     * 5.1. 判断s2是否为空，若为空，则将s1中的元素全部出栈，并入栈s2
     * 5.2. 返回s2的栈顶元素
     */
    class MyQueue {
        private final Deque<Integer> s1;
        private final Deque<Integer> s2;

        public MyQueue() {
            s1 = new ArrayDeque<>();
            s2 = new ArrayDeque<>();
        }

        public void push(int x) {
            s1.push(x);
        }

        public int pop() {
            if (s2.isEmpty())
                while (!s1.isEmpty())
                    s2.push(s1.pop());
            return s2.pop();
        }

        public int peek() {
            if (s2.isEmpty())
                while (!s1.isEmpty())
                    s2.push(s1.pop());
            return s2.peek();
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        MyQueue solution = new ImplementQueueUsingStacks().new MyQueue();
        // put your test code here

    }
}