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

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //这里再次祭出了前面讲过的保命大招 Dummy Node。
        //因为合并后的新链表，头节点到底是谁（是 l1 的第一个，还是 l2 的第一个？）我们在最开始是不知道的。
        //所以我们先凭空捏造一个 dummy，让 curr 站在上面。以后不管谁被挑中，直接往 curr 后面接就行了。
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        //注意这里的条件是 &&（并且）。意思是：只要这两沓牌里都还有牌，比赛就继续。 
        //一旦其中有一沓牌被抽空了（变成了 null），比较就无法进行了，必须立刻停手。
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1; // l1 比较小，把 l1 接到新队伍的末尾
                l1 = l1.next;   // l1 的这名队员出列了，l1 指针赶紧走向下一个队员
            } else {
                curr.next = l2; // 反之，把 l2 接进来
                l2 = l2.next;   // l2 指针走向下一个队员
            }
            //队伍整体前移
            curr = curr.next;
        }
        //循环结束，说明一定有一沓牌已经被抽空了，另一沓牌没空。
        //如果 l1 没空，就把剩下的 l1 全接上；否则就把 l2 全接上。
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}