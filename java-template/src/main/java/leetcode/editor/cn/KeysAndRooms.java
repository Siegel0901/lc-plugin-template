package leetcode.editor.cn;

import java.util.*;

public class KeysAndRooms {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Set<Integer> visited = new HashSet<>();

        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            dfs(rooms, 0);
            return visited.size() == rooms.size();
        }

        void dfs(List<List<Integer>> rooms, int n) {
            if (visited.contains(n))
                return;
            visited.add(n);
            for (int i : rooms.get(n))
                dfs(rooms, i);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            Set<Integer> visited = new HashSet<>();
            Deque<Integer> dq = new ArrayDeque<>();
            dq.offer(0);
            visited.add(0);
            while (!dq.isEmpty()) {
                Integer cur = dq.poll();
                for (int i : rooms.get(cur)) {
                    if (visited.contains(i))
                        continue;
                    dq.offer(i);
                    visited.add(i);
                }
            }
            return visited.size() == rooms.size();
        }
    }


    public static void main(String[] args) {
        Solution solution = new KeysAndRooms().new Solution();
        // put your test code here

    }
}