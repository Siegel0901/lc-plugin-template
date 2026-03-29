package leetcode.editor.cn;

import java.util.*;

public class MinimumGeneticMutation {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minMutation(String startGene, String endGene, String[] bank) {
            Set<String> geneBank = new HashSet<>();
            Collections.addAll(geneBank, bank);
            if (!geneBank.contains(endGene))
                return -1;
            Set<String> s1 = new HashSet<>();
            Set<String> s2 = new HashSet<>();
            Set<String> visited = new HashSet<>();
            s1.add(startGene);
            visited.add(startGene);
            s2.add(endGene);
            visited.add(endGene);
            int step = 0;
            while (!s1.isEmpty()) {
                step++;
                Set<String> newS1 = new HashSet<>();
                for (String gene : s1) {
                    for (int i = 0; i < gene.length(); i++) {
                        List<String> geneChanges = geneChange(gene, i);
                        for (String geneChange : geneChanges) {
                            if (s2.contains(geneChange))
                                return step;
                            if (visited.contains(geneChange) || !geneBank.contains(geneChange))
                                continue;
                            newS1.add(geneChange);
                            visited.add(geneChange);
                        }
                    }
                }
                s1 = newS1;
                if (s1.size() > s2.size()) {
                    Set<String> t = s1;
                    s1 = s2;
                    s2 = t;
                }
            }
            return -1;
        }

        List<String> geneChange(String gene, int idx) {
            List<String> res = new ArrayList<>();
            char[] ch = gene.toCharArray();
            char[] genes = new char[]{'A', 'C', 'G', 'T'};
            for (char c : genes) {
                ch[idx] = c;
                res.add(new String(ch));
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinimumGeneticMutation().new Solution();
        // put your test code here

    }
}