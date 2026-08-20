package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class ImplementTriePrefixTree {

    //leetcode submit region begin(Prohibit modification and deletion)
//    class Trie {
//        TrieMap map = null;
//
//        public Trie() {
//            map = new TrieMap();
//        }
//
//        public void insert(String word) {
//            map.put(word, new Object());
//        }
//
//        public boolean search(String word) {
//            return map.containsKey(word);
//        }
//
//        public boolean startsWith(String prefix) {
//            return map.hasKeyWithPrefix(prefix);
//        }
//    }
//
//    class TrieMap {
//        final int R = 26;
//        TrieNode root = null;
//
//        class TrieNode {
//            Object val = null;
//            TrieNode[] children = new TrieNode[R];
//        }
//
//        void put(String key, Object val) {
//            root = put(root, key, val, 0);
//        }
//
//        TrieNode put(TrieNode node, String key, Object val, int i) {
//            if (node == null)
//                node = new TrieNode();
//            if (i == key.length()) {
//                node.val = val;
//                return node;
//            }
//            int idx = key.charAt(i) - 'a';
//            node.children[idx] = put(node.children[idx], key, val, i + 1);
//            return node;
//        }
//
//        Object get(String key) {
//            TrieNode x = getNode(root, key);
//            if (x == null || x.val == null)
//                return null;
//            return x.val;
//        }
//
//        boolean containsKey(String key) {
//            return get(key) != null;
//        }
//
//        boolean hasKeyWithPrefix(String prefix) {
//            return getNode(root, prefix) != null;
//        }
//
//        TrieNode getNode(TrieNode node, String key) {
//            TrieNode p = node;
//            for (int i = 0; i < key.length(); i++) {
//                if (p == null)
//                    return null;
//                int idx = key.charAt(i) - 'a';
//                p = p.children[idx];
//            }
//            return p;
//        }
//    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 完整TrieMap实现
     * */
    class TrieMap<V> {
        // ASCII码个数
        private static final int R = 256;
        // 记录TrieMap中的键值对个数
        private int size = 0;
        // TrieMap根节点
        private TrieNode<V> root = null;

        // 节点
        private static class TrieNode<V> {
            // 节点值
            V val = null;
            // 节点的孩子
            TrieNode<V>[] children = new TrieNode[R];
        }

        // 从节点node开始搜索key，如果存在返回对应节点，否则返回null
        private TrieNode<V> getNode(TrieNode<V> node, String key) {
            // p为工作指针,初始值为node节点
            TrieNode<V> p = node;
            // 遍历key中的每一个字符
            for (int i = 0; i < key.length(); i++) {
                // 若p为null,则不存在key对应节点,返回null
                if (p == null)
                    return null;
                // 获取当前字符
                char c = key.charAt(i);
                // p下移至该字符对应的节点
                p = p.children[c];
            }
            // 遍历结束后存在key对应的节点,返回
            return p;
        }

        // **** 增/改 ****

        // 在 Map 中添加 key
        public void put(String key, V val) {
            // 若key不存在
            if (!containsKey(key))
                // 新增键值对
                size++;
            // 若key存在,则修改key对应的val
            root = put(root, key, val, 0);
        }

        // 定义：向以node为根节点的trie树中插入key[i..]，返回插入完成后的根节点
        private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
            // node为空则新建
            if (node == null)
                node = new TrieNode<>();
            // key插入完成
            if (i == key.length()) {
                // 赋值val
                node.val = val;
                // 返回node作为上层递归的子节点
                return node;
            }
            char c = key.charAt(i);
            // 递归插入子节点,并接收返回值
            node.children[c] = put(node.children[c], key, val, i + 1);
            return node;
        }

        // **** 删 ****

        // 删除键 key 以及对应的值
        public void remove(String key) {
            // 若key不存在,则返回null
            if (!containsKey(key))
                return;
            // 在以root为根的trie树中删除键key[0..]
            root = remove(root, key, 0);
            size--;
        }

        // 定义:在以node为根的trie树中删除key[i..],返回删除后的根节点
        private TrieNode<V> remove(TrieNode<V> node, String key, int i) {
            // 若node为null,则返回null
            if (node == null)
                return null;
            // 找到键key
            if (i == key.length()) {
                // 将节点值置空
                node.val = null;
            } else {
                char c = key.charAt(i);
                // 对子节点进行递归删除
                node.children[c] = remove(node.children[c], key, i + 1);
            }
            // 检查node的值是否为空,即是否为键的最后一个节点
            if (node.val != null)
                return node;
            // 检查node是否存在子节点,即是否作为键的中间节点
            for (char j = 0; j < R; j++)
                if (node.children[j] != null)
                    return node;
            // 既不是键的最后一个节点,也不是键的中间节点,则删除
            return null;
        }

        // **** 查 ****

        // 搜索 key 对应的值，不存在则返回 null
        // get("the") -> 4
        // get("tha") -> null
        public V get(String key) {
            // 搜索key对应的节点
            TrieNode<V> x = getNode(root, key);
            // 节点为null或者节点的val为null,则说明key不存在
            if (x == null || x.val == null)
                return null;
            // key存在,返回key对应的val
            return x.val;
        }

        // 判断 key 是否存在在 Map 中
        // containsKey("tea") -> false
        // containsKey("team") -> true
        public boolean containsKey(String key) {
            // 获取key对应的val,val存在key存在,val不存在key不存在
            return get(key) != null;
        }

        // 在 Map 的所有键中搜索 query 的最短前缀
        // shortestPrefixOf("themxyz") -> "the"
        public String shortestPrefixOf(String query) {
            // p为工作指针,初始为根节点
            TrieNode<V> p = root;
            // 遍历query中的每个字符
            for (int i = 0; i < query.length(); i++) {
                // 若p为null,则说明没有找到前缀,返回空字符串""
                if (p == null)
                    return "";
                // 若p不为null且p节点值存在,则说明在所有键中找到了query的最小前缀
                if (p.val != null)
                    return query.substring(0, i);
                // 取当前字符
                char c = query.charAt(i);
                // p下移至该字符对应的节点
                p = p.children[c];
            }
            // 遍历结束后,未检查p节点,需要判断query本身是否为一个键
            if (p != null && p.val != null)
                return query;
            // 没有找到前缀,返回空字符串
            return "";
        }

        // 在 Map 的所有键中搜索 query 的最长前缀
        // longestPrefixOf("themxyz") -> "them"
        public String longestPrefixOf(String query) {
            // p为工作指针,初始为根节点
            TrieNode<V> p = root;
            // 记录最长前缀的长度
            int maxLen = 0;
            // 遍历query中的每一个字符
            for (int i = 0; i < query.length(); i++) {
                // 若p为null,则循环结束,根据maxLen返回前缀
                if (p == null)
                    break;
                // 若p不为null且p的val存在,则更新最长前缀
                if (p.val != null)
                    maxLen = i;
                // 获取当前字符
                char c = query.charAt(i);
                // p下移至当前字符对应的节点
                p = p.children[c];
            }
            // 循环结束后,未检查p节点,需要判断query是否为一个键
            if (p != null && p.val != null)
                return query;
            // 根据最长前缀长度返回query的最长前缀
            return query.substring(0, maxLen);
        }

        // 搜索所有前缀为 prefix 的键
        /*
         * 思路：
         * 1. 先通过getNode找到prefix对应的节点x
         * 2. 再通过遍历多叉树的方式，以x为根节点，遍历所有前缀为prefix的节点
         * */
        // keysWithPrefix("th") -> ["that", "the", "them"]
        public List<String> keysWithPrefix(String prefix) {
            List<String> res = new ArrayList<>();
            // 找到prefix对应的节点x
            TrieNode<V> x = getNode(root, prefix);
            if (x == null)
                return res;
            // 遍历以x为根节点的trie树,找到所有以prefix为前缀的所有键
            traverse(x, new StringBuilder(prefix), res);
            return res;
        }

        // 遍历以node为根节点的trie树，找到所有健
        private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
            // 递归出口：遍历到叶子节点
            if (node == null)
                return;
            // 若node的val非空，则说明找到了一个键，添加到res中
            if (node.val != null)
                res.add(path.toString());
            // 回溯算法遍历所有子节点
            for (char c = 0; c < R; c++) {
                // 做选择
                path.append(c);
                // 遍历以子节点为根的trie树
                traverse(node.children[c], path, res);
                // 撤销选择
                path.deleteCharAt(path.length() - 1);
            }
        }

        // 判断是和否存在前缀为 prefix 的键
        // hasKeyWithPrefix("tha") -> true
        // hasKeyWithPrefix("apple") -> false
        public boolean hasKeyWithPrefix(String prefix) {
            // 找前缀不需要该节点的val存在，节点存在则prefix存在
            return getNode(root, prefix) != null;
        }

        // 通配符 . 匹配任意字符，搜索所有匹配的键
        // keysWithPattern("t.a.") -> ["team", "that"]
        public List<String> keysWithPattern(String pattern) {
            List<String> res = new ArrayList<>();
            traverse(root, new StringBuilder(), pattern, 0, res);
            return res;
        }

        // 遍历函数，尝试在[以node为根的trie树中]匹配pattern[i...]
        private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
            if (node == null)
                return;
            if (i == pattern.length()) {
                // pattern匹配完成
                if (node.val != null)
                    res.add(path.toString());
                return;
            }
            char c = pattern.charAt(i);
            if (c == '.') {
                // pattern[i]是通配符，可以变化为任意字符
                for (char j = 0; j < R; j++) {
                    path.append(j);
                    traverse(node.children[j], path, pattern, i + 1, res);
                    path.deleteCharAt(path.length() - 1);
                }
            } else {
                // pattern[i]是普通字符c
                path.append(c);
                traverse(node.children[c], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        }

        // 通配符 . 匹配任意字符，判断是否存在匹配的键
        // hasKeyWithPattern(".ip") -> true
        // hasKeyWithPattern(".i") -> false
        public boolean hasKeyWithPattern(String pattern) {
            // 偷懒实现，但复杂度较高，没必要得到所有匹配pattern的键，只需要判断有没有
            // return !keysWithPattern(pattern).isEmpty();
            // 从root节点开始匹配pattern[0..]
            return traverse(root, pattern, 0);
        }

        private boolean traverse(TrieNode<V> node, String pattern, int i) {
            // 当前节点为空,则匹配失败
            if (node == null)
                return false;
            // 所有字符都匹配完了
            if (i == pattern.length())
                // 通过node.val是否为空判断是否匹配到一个键
                return node.val != null;
            // 取pattern第i位
            char c = pattern.charAt(i);
            // 没有遇到通配符
            if (c != '.')
                // 从node.children[c]节点开始匹配pattern[i+1..]
                return traverse(node.children[c], pattern, i + 1);
            else {
                // c为通配符
                for (char j = 0; j < R; j++)
                    // pattern[i]可以变化为任意字符,尝试所有可能,只要找到一个匹配成功就返回
                    if (traverse(node.children[j], pattern, i + 1))
                        return true;
            }
            // 都没有匹配
            return false;
        }

        // 返回 Map 中键值对的数量
        public int size() {
            return size;
        }

    }

    public static void main(String[] args) {
//        Solution solution = new ImplementTriePrefixTree().new Solution();
        // put your test code here

    }
}