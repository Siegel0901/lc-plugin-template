package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RevealCardsInIncreasingOrder {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * res顶部放到sorted顶部，res顶部放到res底部，最终得到递增序列sorted
         * res:[2,13,3,11,5,17,7] -> sorted:[]
         * res:[3,11,5,17,7,13] -> sorted:[2]
         * res:[5,17,7,13,11] -> sorted:[2,3]
         * res:[7,13,11,17] -> sorted:[2,3,5]
         * res:[11,17,13] -> sorted:[2,3,5,7]
         * res:[13,17] -> sorted:[2,3,5,7,11]
         * res:[17] -> sorted:[2,3,5,7,11,13]
         * res:[] -> sorted:[2,3,5,7,11,13,17]
         * res底部放到res顶部，递减序列sorted顶部放到res顶部，最终得到原序列
         * sorted:[17,13,11,7,5,3,2] -> res:[]
         * sorted:[13,11,7,5,3,2] -> res:[17]
         * sorted:[11,7,5,3,2] -> res:[13,17]
         * sorted:[7,5,3,2] -> res:[11,17,13]
         * sorted:[5,3,2] -> res:[7,13,11,17]
         * sorted:[3,2] -> res:[5,17,7,13,11]
         * sorted:[2] -> res:[3,11,5,17,7,13]
         * sorted:[] -> res:[2,13,3,11,5,17,7]
         * */
        public int[] deckRevealedIncreasing(int[] deck) {
            Arrays.sort(deck);
            Deque<Integer> res = new ArrayDeque<>();
            // 默认升序,倒着遍历即为降序
            for (int i = deck.length - 1; i >= 0; i--) {
                // 将res的底部放到res顶部
                if (!res.isEmpty())
                    res.offerFirst(res.pollLast());
                // sorted顶部放到res顶部
                res.offerFirst(deck[i]);
            }
            for (int i = 0; i < deck.length; i++)
                deck[i] = res.pollFirst();
            return deck;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RevealCardsInIncreasingOrder().new Solution();
        // put your test code here

    }
}