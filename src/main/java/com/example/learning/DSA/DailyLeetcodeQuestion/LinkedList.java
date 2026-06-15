package com.example.learning.DSA.DailyLeetcodeQuestion;

import java.util.List;

public class LinkedList {
    public static void main(String[] args) {
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode() {
        }
    }

    public static ListNode deleteMiddle(ListNode head) {
        if(head==null || head.next==null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = new ListNode(-1);
        while (fast !=null && fast.next!=null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        prev.next = slow.next;
        slow.next = null;

        return head;

    }
}


