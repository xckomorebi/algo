import utils.ListNode;

public class LinkedList {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();

        l.mergeSort(ListNode.fromArray(new int[] { 4, 3, 2, 1 }));
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

    /**
     * ReOrder Linked List
     * <p>
     * Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null
     * to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null
     */
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // get midpoint
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        // reverse LinkedList 2
        ListNode prev = null;
        ListNode next;
        while (head2 != null) {
            next = head2.next;
            head2.next = prev;
            prev = head2;
            head2 = next;
        }

        // merge head with prev
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (prev != null) {
            cur.next = head;
            head = head.next;
            cur.next.next = prev;
            prev = prev.next;
            cur = cur.next.next;
        }
        if (head != null) {
            cur.next = head;
        }

        return dummy.next;
    }

    /**
     * Partition Linked List
     * <p>
     * Given a linked list and a target value T, partition it such that all nodes
     * less than T are listed before the nodes larger than or equal to target value
     * T. The original relative order of the nodes in each of the two partitions
     * should be preserved.
     */
    public ListNode partition(ListNode head, int target) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;
        ListNode cur = head;

        while (cur != null) {
            if (cur.value < target) {
                cur1.next = cur;
                cur1 = cur;
            } else {
                cur2.next = cur;
                cur2 = cur;
            }
            cur = cur.next;
        }
        cur2.next = null;
        cur1.next = dummy2.next;
        return dummy1.next;
    }

    /**
     * Merge Sort Linked List
     * <p>
     * Given a singly-linked list, where each node contains an integer value, sort
     * it in ascending order. The merge sort algorithm should be used to solve this
     * problem.
     */
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        head = mergeSort(head);
        head2 = mergeSort(head2);

        ListNode newHead = merge2(head, head2);
        return newHead;
    }

    private ListNode merge2(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if (head1 != null) {
            cur.next = head1;
        } else {
            cur.next = head2;
        }

        return dummy.next;
    }

    /**
     * Add Two Numbers
     * <p>
     * You are given two linked lists representing two non-negative numbers. The
     * digits are stored in reverse order and each of their nodes contain a single
     * digit. Add the two numbers and return it as a linked list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.value + l2.value + carry;
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }

        if (l1 != null || l2 != null) {
            l1 = (l2 != null) ? l2 : l1;
            while (l1 != null) {
                int sum = l1.value + carry;
                cur.next = new ListNode(sum % 10);
                carry = sum / 10;
                l1 = l1.next;
                cur = cur.next;
            }
        }

        if (carry != 0) {
            cur.next = new ListNode(carry);
        }

        return dummy.next;
    }

    /**
     * Check If Linked List Is Palindrome
     * <p>
     * Given a linked list, check whether it is a palindrome.
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // find middle point
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        // reverse second half
        ListNode prev = null;
        ListNode next;
        while (head2 != null) {
            next = head2.next;
            head2.next = prev;
            prev = head2;
            head2 = next;
        }

        // check palindrome

        while (prev != null) {
            if (head.value != prev.value) {
                return false;
            }
            head = head.next;
            prev = prev.next;
        }

        return true;
    }

    /**
      * Remove Linked List Elements
      * <p>
      * Remove all elements from a linked list of integers that have value val.
    */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head != null) {
            if (head.value != val) {
                cur.next = head;
                head = head.next;
                cur = cur.next;
            } else {
                head = head.next;
            }
        }
        cur.next = null;
        return dummy.next;
    }
}
