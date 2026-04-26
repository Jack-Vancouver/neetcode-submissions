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
    public void reorderList(ListNode head) {
        // 0. 极端情况防御：如果链表为空，或者只有一个/两个节点，根本不需要重排
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // ==========================================
        // 第一步：找中点 (龟兔赛跑 / 快慢指针)
        // ==========================================
        ListNode slow = head;
        ListNode fast = head;
        // 兔子每次跑两步，乌龟跑一步。兔子跑到终点时，乌龟刚好停在正中间！
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // ==========================================
        // 第二步：劈开链表，并原地翻转后半段 (你最熟悉的配方)
        // ==========================================
        // slow 目前停在中点。中点的下一个节点，就是后半段的开头。
        ListNode secondHalf = slow.next;
        // 【极其致命的一步】：把前半段和后半段的绳子彻底剪断！否则等下合并会无限死循环！
        slow.next = null; 

        // 开始反转后半段 (经典的 prev, curr, next 三剑客)
        ListNode prev = null;
        ListNode curr = secondHalf;
        while (curr != null) {
            ListNode nextTemp = curr.next; // 1. 记下未来
            curr.next = prev;              // 2. 原地掉头
            prev = curr;                   // 3. prev 往前走
            curr = nextTemp;               // 4. curr 往前走
        }
        // 反转结束后，prev 就是后半段新的头节点。

        // ==========================================
        // 第三步：像拉拉链一样，交叉合并两个链表
        // ==========================================
        ListNode first = head; // 前半段的头
        ListNode second = prev; // 后半段的头

        // 只要后半段还有节点，就继续“拉拉链”
        while (second != null) {
            // 1. 先派探子把两人各自的下一个节点记住，防止失联
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            // 2. 交叉牵手：前半段的第一人 -> 后半段的第一人 -> 前半段的第二人
            first.next = second;
            second.next = tmp1;

            // 3. 两人双双往后挪一步，准备下一轮咬合
            first = tmp1;
            second = tmp2;
        }
    }
}