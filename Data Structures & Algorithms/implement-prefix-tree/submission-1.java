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


//  1. 房间的装修队：public TrieNode() { ... }
//     public TrieNode() {
//         children = new TrieNode[26];
//         isEnd = false;
//     }
// •	这是什么：这是 TrieNode 类的构造函数。你可以把它想象成“装修队的图纸”。
// •	它在干啥：当我们用代码喊一句 new TrieNode()（也就是下令“建一个新房间”）时，装修队就会照着这段代码干活：
// 	1.	children = new TrieNode[26];：立刻在墙上真刀真枪地凿出 26 个挂钩（在内存里切出 26 个数组空间）。
//  如果你不写这句，children 就只是个概念，一用就会报错（空指针异常）。
// 	2.	isEnd = false;：把地上的“单词结尾”红标签撕掉，确保每个新房间默认都是普通的路过房间。

// 2. 规划一楼大厅的位置：private TrieNode root;
//     // 2. 声明根节点
//     private TrieNode root;
// •	这是什么：这是一个全局变量声明。
// •	它在干啥：一棵树必须有一个根，一栋迷宫大楼必须有一个“一楼大厅入口”。
//  这行代码就是在图纸上画了一个圈，说：“我需要一个叫做 root 的入口房间。”
// •	注意：此时此刻，大厅还没建出来！它只是个虚空的名字，这时候如果进去，会踩空掉进黑洞（root 此时是 null）。

// 3. 迷宫大厦正式开工：public PrefixTree() { ... }
//     // 3. 构造函数：初始化大树的树根
//     public PrefixTree() {
//         root = new TrieNode();
//     }

// •	这是什么：这是整个 PrefixTree（前缀树）这栋大厦的构造函数。
// •	它在干啥：当别人在外面调用 new PrefixTree()，想要使用你的前缀树时，这段代码会第一时间被执行。
// •	root = new TrieNode();：刚才说的那个虚空的入口，现在终于被实体化了！
// •	它召唤了前面说的“装修队”，真真切切地造出了第一个拥有 26 扇门的房间，并把它安在 root 的位置上。
// •	为什么必须有这一步：大厦开业，总得有个一楼大厅吧！如果没有这句代码，用户试图往树里插入第一个单词时，连可以进的门都没有，程序直接就崩溃了。
