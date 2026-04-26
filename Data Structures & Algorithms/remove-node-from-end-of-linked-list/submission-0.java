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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. 祭出保命神器 Dummy Node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 2. 龟兔一起站在起跑线 (Dummy 节点)
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // 3. 兔子先跑，拉开差距。
        // 因为我们要找的是倒数第 n 个节点，但我们要站在它的【前一个节点】才能动手删它。
        // 所以我们要让兔子先跑 n + 1 步，拉开距离。
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // 4. 尺子成型，大家一起往前挪，直到兔子掉下悬崖 (fast == null)
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // 5. 此时乌龟 (slow) 刚好站在被删节点的【正前方】
        // 核心微操：扯断绳子，绕过目标节点，连向下一个
        slow.next = slow.next.next;
        
        // 6. 功成身退，返回真正的头节点
        return dummy.next;
    }
}
