package submitted;

public class FindNodeBeginAtLoopLinkedList {
    ListNode FindBeginning(ListNode head) {
        ListNode n1 = head;
        ListNode n2 = head;

        // Find meeting point
        while (n2.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
            if (n1 == n2) {
                break;
            }
        }

        // Error check - there is no meeting point, and therefore no loop
        if (n2.next == null) {
            return null;
        }

         /* Move n1 to Head. Keep n2 at Meeting Point. Each are k steps
         from the Loop Start. If they move at the same pace, they must
         meet at Loop Start. */
        n1 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        // Now n2 points to the start of the loop.
        return n2;
    }
}
