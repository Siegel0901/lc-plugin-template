package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueueReconstructionByHeight {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路一：插入排序
     * 1. 对h进行从大到小排序，h相同的人按照k从小到大排序
     * 2. 排序完根据k用插入排序对结果进行修正
     * 3. people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 时间复杂度：O(nlogn + n^2)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, (o1, o2) -> {
                // h相同
                if (o1[0] == o2[0])
                    // 按照k从小到大排序
                    return o1[1] - o2[1];
                // 按照h从大到小排序
                return o2[0] - o1[0];
            });
            // 第一遍排序后people = [[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]]
/*
            insertSort(people);
            return people;
*/
            List<int[]> list = new ArrayList<>();
            for (int[] person : people)
                list.add(person[1], person);
            return list.toArray(new int[][]{});
        }

/*
        // 对标准的插入排序逻辑稍作修改
        private void insertSort(int[][] people) {
            int n = people.length;
            // sortedIdx表示[0,sortedIdx)是有序的
            int sortedIdx = 0;
            */
        /*
         * 插入排序过程：
         * sortedIdx=0, people = [[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]],sortedIdx=1
         * sortedIdx=1, people = [[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]],sortedIdx=2
         * sortedIdx=2, people = [[7,0],[6,1],[7,1],[5,0],[5,2],[4,4]],sortedIdx=3
         * sortedIdx=3, people = [[5,0],[7,0],[6,1],[7,1],[5,2],[4,4]],sortedIdx=4
         * sortedIdx=4, people = [[5,0],[7,0],[5,2],[6,1],[7,1],[4,4]],sortedIdx=5
         * sortedIdx=5, people = [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]],sortedIdx=6
         * *//*

            while (sortedIdx < n) {
                */
        /*
         * 将people[sortedIdx]向前移动
         * 现在这个人前面有sortedIdx(索引从0开始)个比他高的人
         * 目标是前面有k个比他高的人
         * *//*

                int k = people[sortedIdx][1];
                for (int i = sortedIdx; i > 0; i--) {
                    // swap(people[i], people[i-1])
                    if (k < i) {
                        int[] tmp = people[i];
                        people[i] = people[i - 1];
                        people[i - 1] = tmp;
                    } else
                        break;
                }
                sortedIdx++;
            }
        }
*/
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new QueueReconstructionByHeight().new Solution();
        // put your test code here
        solution.reconstructQueue(new int[][]{
                new int[]{7, 0},
                new int[]{4, 4},
                new int[]{7, 1},
                new int[]{5, 0},
                new int[]{6, 1},
                new int[]{5, 2},
        });
    }
}