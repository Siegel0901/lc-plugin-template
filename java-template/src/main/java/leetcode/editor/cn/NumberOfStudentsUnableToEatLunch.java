package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfStudentsUnableToEatLunch {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路二：找规律
         * 吃不上饭的情况：
         * 1. 剩下的学生都想吃1，但是栈顶是0
         * 2. 剩下的学生都想吃0，但是栈顶是1
         * 因为学生队伍可以循环，所以栈顶的三明治可以被所有学生挑选
         * 统计学生队伍中0和1的需求情况，判断是否会吃不上饭
         */
        public int countStudents(int[] students, int[] sandwiches) {
            // studentCount索引代表0和1，统计吃0和吃1的学生数量
            int[] studentCount = new int[2];
            for (int type : students)
                studentCount[type]++;
            for (int type : sandwiches) {
                // 若吃该类型三明治的学生为0，则说明没人想吃栈顶的三明治
                if (studentCount[type] == 0)
                    // 返回当前剩余学生数量
                    return studentCount[0] + studentCount[1];
                // 不为0，则学生吃三明治
                studentCount[type]--;
            }
            return 0;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        /**
         * 思路一：利用队列模拟
         */
        public int countStudents(int[] students, int[] sandwiches) {
            Deque<Integer> q = new ArrayDeque<>();
            for (int student : students)
                q.offer(student);
            for (int i = 0; i < sandwiches.length; i++) {
                int count = 0;
                while (q.peek() != sandwiches[i]) {
                    q.offer(q.poll());
                    count++;
                    if (count == q.size())
                        return count;
                }
                q.poll();
            }
            return 0;
        }
    }


    public static void main(String[] args) {
        Solution solution = new NumberOfStudentsUnableToEatLunch().new Solution();
        // put your test code here
        System.out.println(solution.countStudents(new int[]{
                1, 1, 1, 0, 0, 1
        }, new int[]{
                1, 0, 0, 0, 1, 1
        }));
    }
}