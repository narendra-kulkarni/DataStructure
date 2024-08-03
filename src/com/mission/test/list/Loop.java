package com.mission.test.list;

public class Loop {

    public int findLoopLength(Node head) {
        // loop detection
        if (head == null || head.next == null)
            return 0;

        Node slow = head;
        Node fast = head.next;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;

            if (fast != null)
                fast = fast.next;

            if (slow == fast)
                break;
        }

        // Note: slow & fast pointers can be pointing to
        // any node within the loop

        // start of the loop
        if (fast != null) {
            slow = head;
            fast = fast.next;

            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            System.out.println("Start of the loop : " + slow.data);
        }

        // loop length
        if (fast == null)
            return 0;
        else {
            int count = 1;
            while (fast.next != slow) {
                count++;
                fast = fast.next;
            }

            return count;
        }
    }

    /**************************************************************/

    public static void main(String[] args) {
        Loop l = new Loop();
        Node head = createLoopedList();
        System.out.println("4 5 6 -> 7 8 9 10 (loop to 7)");
        System.out.println("Length of loop : " + l.findLoopLength(head));
    }

    static Node createLoopedList() {
        Node head = new Node(4);
        head.next = new Node(5);
        head.next.next = new Node(6);
        Node first = new Node(7);
        head.next.next.next = first;
        first.next = new Node(8);
        first.next.next = new Node(9);
        Node last = new Node(10);
        first.next.next.next = last;
        last.next = first;
        return head;
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}
