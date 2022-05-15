package week03;

import common.ListNode;

public class 合并K个升序链表 {

    private ListNode[] nums;

    private int tail = 1;

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int length = lists.length;
        nums = new ListNode[length + 1];
        for (int i=1; i<=length; i++) {
            insert(lists[i-1]);
        }

        ListNode res = new ListNode();
        ListNode head = res;
        while (tail > 1) {
            ListNode node = popHead();
            res.next = new ListNode(node.val);
            res = res.next;
            if (node.next != null) {
                insert(node.next);
            }
        }

        return head.next;
    }

    private void insert(ListNode node) {
        if (node == null) {
            return;
        }
        nums[tail++] = node;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = tail - 1;
        while (index > 1) {
            int father = index / 2;
            if (nums[father].val > nums[index].val) {
                ListNode temp = nums[index];
                nums[index] = nums[father];
                nums[father] = temp;
            }
            index = father;
        }
    }

    private void heapifyDown() {
        int index = 1;
        while (index*2 <= tail - 1) {
            int child = index * 2;
            int other = child + 1;
            if (other <= tail-1 && nums[other].val < nums[child].val) {
                child = other;
            }
            if (child <= tail-1 &&nums[index].val > nums[child].val) {
                ListNode temp = nums[index];
                nums[index] = nums[child];
                nums[child] = temp;
            }
            index = child;
        }
    }

    private ListNode popHead() {
        if (tail-1 < 1) {
            return null;
        }
        ListNode head = nums[1];
        nums[1] = nums[tail-1];
        nums[tail-1] = head;
        tail--;
        heapifyDown();
        return head;
    }


    public static void main(String[] args) {
        合并K个升序链表 t = new 合并K个升序链表();

//        ListNode ka1 = new ListNode(1);
//        ka1.next = new ListNode(4);
//        ka1.next.next = new ListNode(5);
//
//        ListNode ka2 = new ListNode(1);
//        ka2.next = new ListNode(3);
//        ka2.next.next = new ListNode(4);
//
//
//        ListNode ka3 = new ListNode(2);
//        ka3.next = new ListNode(6);
////        ka3.next.next = new ListNode(4);



        ListNode ka1 = new ListNode(-8);
        ka1.next = new ListNode(-7);
        ka1.next.next = new ListNode(-4);

        ListNode ka2 = new ListNode(-2);


        ListNode ka3 = new ListNode(-10);
        ka3.next = new ListNode(-10);
        ka3.next.next = new ListNode(-7);

        ListNode ka4 = new ListNode(2);

        ListNode listNode = t.mergeKLists(new ListNode[]{ka1, ka2, ka3, ka4});
        System.out.println(listNode);


    }

}
