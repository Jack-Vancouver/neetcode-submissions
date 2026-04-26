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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        // 步长，控制谁和谁 PK
        // 初始步长为 1：0和1打，2和3打...
        // 步长为 2 时：0和2打，4和6打...
        int interval = 1; 
        
        while (interval < lists.length) {
            // 按照步长，两两合并
            for (int i = 0; i + interval < lists.length; i = i + interval * 2) {
                // 把 PK 胜利的结果，存回到靠前的位置 i
                lists[i] = merge2Lists(lists[i], lists[i + interval]);
            }
            // 步长翻倍，准备下一轮比赛
            interval *= 2; 
        }
        
        // 最后的总冠军一定坐在 0 号位
        return lists[0];
    }

    // 这是一个标准的辅助函数：合并 2 个有序链表 (LeetCode 21)
    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}