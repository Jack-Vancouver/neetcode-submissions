class PrefixTree {
    
    // 1. 定义节点内部类（隐藏实现细节，不需要对外暴露）
    class TrieNode {
        TrieNode[] children; // 26 个挂钩
        boolean isEnd;       // 红标签，标记单词结尾

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    // 2. 声明根节点
    private TrieNode root;

    // 3. 构造函数：初始化大树的树根
    public PrefixTree() {
        root = new TrieNode();
    }

    // 4. 插入功能
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a'; // 计算坑位 (0-25)
            // 如果没路，就修一条路
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            // 顺着路往下走
            curr = curr.children[index];
        }
        // 走完最后一个字母，贴上红标签
        curr.isEnd = true;
    }

    // 5. 精确查找
    public boolean search(String word) {
        TrieNode node = findNode(word);
        // 必须走得通，且最后一个节点贴了红标签，才算完整的单词
        return node != null && node.isEnd;
    }

    // 6. 前缀查找
    public boolean startsWith(String prefix) {
        TrieNode node = findNode(prefix);
        // 只要能顺着前缀走通就行，不管有没有红标签
        return node != null;
    }

    // --- 核心辅助函数：复用底层寻路逻辑 ---
    private TrieNode findNode(String str) {
        TrieNode curr = root;
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            // 如果路断了，说明不存在
            if (curr.children[index] == null) {
                return null;
            }
            curr = curr.children[index];
        }
        return curr; // 返回走到的最后一个节点
    }
}

/**
 * Your PrefixTree object will be instantiated and called as such:
 * PrefixTree obj = new PrefixTree();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */