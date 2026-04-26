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
    public ListNode reverseList(ListNode head) {
        // 1. 初始化两个指针
        ListNode prev = null; // 站在当前节点的前面
        ListNode curr = head; // 站在当前节点上

        // 2. 开始从头到尾遍历链表
        while (curr != null) {
            // 【第一步：保存未来】
            // 扯断绳子前，先把 curr 的下一个节点存起来，不然等下找不到路了
            ListNode nextTemp = curr.next; 

            // 【第二步：原地掉头】
            // 把 curr 的绳子，从指向后面，硬生生拽过来指向前面 (prev)
            curr.next = prev; 

            // 【第三步：整体大步往前走】
            // prev 和 curr 兄弟俩一起往前挪一步，准备处理下一个节点
            prev = curr;      // prev 走到 curr 的位置
            curr = nextTemp;  // curr 走到刚才保存的下一个节点的位置
        }

        // 3. 循环结束时，curr 已经走到 null 了，此时 prev 正好站在原来链表的最后一位
        // 它就是反转后的新头节点！
        return prev;
    }
}