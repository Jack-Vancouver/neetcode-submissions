public class Solution {
    public boolean isValid(String s) {
        // 1. 准备一个“纸箱”（栈），用来装还没被匹配的【左括号】
        Stack<Character> stack = new Stack<>();
        
        // 2. 准备一本“配对字典”（哈希表）
        // Key（键）是【右括号】，Value（值）是对应的【左括号】
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');

        // 3. 把字符串拆成一个个字符，开始从左到右遍历
        for (char c : s.toCharArray()) {
            
            // 【情况 A】：如果当前字符是字典里的 Key（说明它是一个【右括号】）
            if (closeToOpen.containsKey(c)) {
                
                // 开始查验：
                // 1. 箱子不能是空的 (!stack.isEmpty())，空了说明没有左括号来配对。
                // 2. 箱子最顶上的括号 (stack.peek())，必须刚好等于字典里规定的那个左括号。
                if (!stack.isEmpty() && stack.peek() == closeToOpen.get(c)) {
                    // 匹配成功！把最顶上的左括号拿出来扔掉（消除）
                    stack.pop();
                } else {
                    // 匹配失败！直接判定为非法字符串
                    return false;
                }
                
            } 
            // 【情况 B】：如果当前字符不是字典里的 Key（说明它是一个【左括号】）
            else {
                // 左括号没有任何商量的余地，直接扔进箱子里等着
                stack.push(c);
            }
        }
        
        // 4. 循环结束，检查箱子是不是空的。
        // 如果全消完了（箱子空了），说明完美合法，返回 true。
        // 如果箱子里还有剩的左括号（比如输入是 "(("），返回 false。
        return stack.isEmpty();
    }
}