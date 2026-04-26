/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public boolean hasCycle(ListNode head) {
        // 1. 龟兔同时站上起跑线
        // fast 是兔子，slow 是乌龟。一开始大家都站在链表的头部。
        ListNode fast = head;
        ListNode slow = head;

        // 2. 【核心防坑点】：安全护航检查
        // 为什么只查 fast，不查 slow？
        // 因为兔子跑在最前面！只要兔子没掉下悬崖，乌龟绝对是安全的。
        while (fast != null && fast.next != null) {
            
            // 3. 兔子跑两步，乌龟跑一步
            fast = fast.next.next; 
            slow = slow.next;      
            
            // 4. 【灵魂拷问】：他们相遇了吗？
            // 注意：这里用的是 ==，比较的是它俩是不是【同一个物理节点】（内存地址相同），
            // 而不是比较里面的数字 (val) 是不是相等！
            if (fast == slow) {
                return true; // 兔子套圈了！实锤有环！
            }
        }
        
        // 5. 比赛顺利结束
        // 如果 while 循环被打破了，说明兔子踩到了 null（终点线）。
        // 既然有终点，说明这条路绝对是一条单行道，没环。
        return false;
    }
}