package Recursion;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode removeElements(ListNode head, int val) {

        // 头节点需要删除
        while (head != null && head.val == val) {
            ListNode node = head;
            head = head.next;
            node.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            // 如果是找到需要删除的元素了，不需要进行prev = prev.next 操作，否则会漏掉元素未检查
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }


}
