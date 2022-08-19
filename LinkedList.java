import utils.ListNode;

public class LinkedList {
    public static void main(String[] args) {
    }

    /**
     * Reverse Linked List (iterative)
     * <p>
     * Reverse a singly-linked list iteratively.
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * Reverse Linked List (recursive)
     * <p>
     * Reverse a singly-linked list recursively.
     */
    public ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse2(head);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    /**
     * Middle Node Of Linked List
     * <p>
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            head = head.next;
        }
        return head;
    }

    /**
     * Check If Linked List Has A Cycle
     * <p>
     * Check if a given linked list has a cycle. Return true if it does, otherwise
     * return false.
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            head = head.next;
            if (fast == head) {
                return true;
            }
        }
        return true;
    }

    /**
     * Insert In Sorted Linked List
     * <p>
     * Insert a value in a sorted linked list.
     */
    public ListNode insert(ListNode head, int value) {
        if (head == null) {
            return new ListNode(value);
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.value < value) {
            cur = cur.next;
        }
        ListNode node = new ListNode(value);
        node.next = cur.next;
        cur.next = node;

        return dummy.next;
    }

    /**
      * Merge Two Sorted Linked Lists
      * <p>
      * Merge two sorted lists into one large sorted list.
    */
    public ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one != null && two != null) {
            if (one.value < two.value) {
                cur.next = one;
                one = one.next;
            } else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }

        if (one != null) {
            cur.next = one;
        } else {
            cur.next = two;
        }

        return dummy.next;
    }
}
