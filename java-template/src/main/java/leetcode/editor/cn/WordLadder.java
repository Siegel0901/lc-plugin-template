package leetcode.editor.cn;

import java.util.*;

public class WordLadder {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // 构建单词列表集合
            Set<String> wordBank = new HashSet<>(wordList);
            // 排除目标单词不在集合中的情况
            if (!wordBank.contains(endWord))
                return 0;
            // 索引-字母集合映射表
            Map<Integer, Set<Character>> idxToChs = new HashMap<>();
            // 构建idxToChs
            for (String word : wordList) {
                char[] cWord = word.toCharArray();
                for (int i = 0; i < cWord.length; i++) {
                    Set<Character> chs = idxToChs.getOrDefault(i, new HashSet<>());
                    chs.add(cWord[i]);
                    idxToChs.put(i, chs);
                }
            }
            // 开始BFS
            Deque<String> dq = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            int step = 0;
            dq.offer(beginWord);
            visited.add(beginWord);
            while (!dq.isEmpty()) {
                int sz = dq.size();
                for (int i = 0; i < sz; i++) {
                    String cur = dq.poll();
                    if (endWord.equals(cur))
                        return step + 1;
                    for (int j = 0; j < cur.length(); j++) {
                        Set<Character> chs = idxToChs.get(j);
                        List<String> neighs = new ArrayList<>();
                        for (Character ch : chs)
                            neighs.add(next(cur, j, ch));
                        for (String neigh : neighs) {
                            if (!wordBank.contains(neigh) || visited.contains(neigh))
                                continue;
                            dq.offer(neigh);
                            visited.add(neigh);
                        }
                    }
                }
                step++;
            }
            return 0;
        }

        String next(String s, int idx, char c) {
            char[] chs = s.toCharArray();
            chs[idx] = c;
            return new String(chs);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new WordLadder().new Solution();
        // put your test code here
        solution.ladderLength("hit", "cog", new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }
}