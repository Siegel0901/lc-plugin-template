package leetcode.editor.cn;

import java.util.*;

public class AccountsMerge {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：BFS
         * 1. 建立eToIdxes映射表，key为email，value为accounts中该email对应的index
         * 2. email对应的index列表中的所有emails都是同一个人
         * 3. 然后对这些emails进行BFS遍历他们的index列表，同时用集合记录已经访问过的email
         * 4. BFS遍历到的email都是同一个人
         */
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            // 建立eToIdxes映射表，key为email，value为accounts中该email对应的index
            Map<String, List<Integer>> eToIdxes = new HashMap<>();
            for (int i = 0; i < accounts.size(); i++) {
                // account为索引i对应的用户信息
                List<String> account = accounts.get(i);
                // account[0]为用户名，account[1]~account[size]为该用户对应的邮箱
                for (int j = 1; j < account.size(); j++) {
                    // 获取每一个邮箱
                    String email = account.get(j);
                    // 获取该邮箱对应的索引
                    List<Integer> idxes = eToIdxes.getOrDefault(email, new ArrayList<>());
                    // 将当前索引加入索引列表
                    idxes.add(i);
                    // 更新映射表
                    eToIdxes.put(email, idxes);
                }
            }

            // 开始合并账户
            List<List<String>> res = new ArrayList<>();
            Set<String> visited = new HashSet<>();

            // 把email当做节点，eToIdxes当做邻接表，以每个email为起点开始BFS遍历，能遍历到的所有email节点都属于同一个人
            for (String email : eToIdxes.keySet()) {
                if (visited.contains(email))
                    continue;
                // LinkedList方便头插法插入username，记录该用户合并后的所有email
                LinkedList<String> mergedEmail = new LinkedList<>();
                Deque<String> dq = new ArrayDeque<>();
                dq.offer(email);
                visited.add(email);
                while (!dq.isEmpty()) {
                    String cur = dq.poll();
                    mergedEmail.add(cur);
                    List<Integer> idxes = eToIdxes.get(cur);
                    for (int idx : idxes) {
                        List<String> account = accounts.get(idx);
                        for (int i = 1; i < account.size(); i++) {
                            String nextEmail = account.get(i);
                            if (visited.contains(nextEmail))
                                continue;
                            dq.offer(nextEmail);
                            visited.add(nextEmail);
                        }
                    }
                }
                // 按字典序排序
                Collections.sort(mergedEmail);
                // 头部添加username
                String username = accounts.get(eToIdxes.get(email).get(0)).get(0);
                mergedEmail.addFirst(username);
                // 加入结果中
                res.add(mergedEmail);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new AccountsMerge().new Solution();
        // put your test code here

    }
}