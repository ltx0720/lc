package week01;

public class T21 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode h = head;
        while (list1 != null || list2 != null) {
            if (list1 != null) {
                if (list2 != null) {
                    int num;
                    if (list1.val <= list2.val) {
                        num = list1.val;
                        list1 = list1.next;
                    } else {
                        num = list2.val;
                        list2 = list2.next;
                    }
                    head.next = new ListNode(num);
                } else {
                    head.next = new ListNode(list1.val);
                    list1 = list1.next;
                }
            }

            else if (list2 != null) {
                head.next = new ListNode(list2.val);
                list2 = list2.next;
            }

            head = head.next;
        }
        return h.next;
    }
}
