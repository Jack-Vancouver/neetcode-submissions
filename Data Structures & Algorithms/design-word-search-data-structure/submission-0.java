class WordDictionary {
    // 1. 依然是那个熟悉的节点类
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    // 2. 添加单词：和上一题 100% 一模一样，闭眼默写
    public void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEnd = true;
    }
    
    // 3. 查找单词：因为可能有 '.'，我们需要祭出 DFS 来递归查找
    public boolean search(String word) {
        // 从根节点出发，从单词的第 0 个字符开始匹配
        return dfs(word, 0, root);
    }

    // --- 核心大招：支持通配符的 DFS 巡逻员 ---
    // 参数：word 要找的词，index 当前查到第几个字符了，node 当前踩在树的哪个节点上
    private boolean dfs(String word, int index, TrieNode node) {
        // Base Case：如果查到了单词的末尾，看看当前踩的节点有没有贴红标签
        if (index == word.length()) {
            return node.isEnd;
        }

        char c = word.charAt(index);

        // 情况 A：遇到万能通配符 '.'
        if (c == '.') {
            // 既然是万能的，我们就把当前节点脚下所有的 26 个岔路口都试一遍
            for (int i = 0; i < 26; i++) {
                // 如果这条路存在，就派分身顺着这条路继续往下找下一个字符 (index + 1)
                if (node.children[i] != null) {
                    // 只要有任何一个分身传回捷报 (true)，直接提前下班！
                    if (dfs(word, index + 1, node.children[i])) {
                        return true;
                    }
                }
            }
            // 26 条路都试过了，全都不通，说明彻底没戏
            return false;
        } 
        // 情况 B：遇到普通字母
        else {
            int charIndex = c - 'a';
            // 如果这条确定的路断了，直接宣判死刑
            if (node.children[charIndex] == null) {
                return false;
            }
            // 路没断，就老老实实顺着这条路往下查下一个字符
            return dfs(word, index + 1, node.children[charIndex]);
        }
    }
}