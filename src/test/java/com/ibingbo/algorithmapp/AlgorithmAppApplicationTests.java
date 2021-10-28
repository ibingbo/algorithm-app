package com.ibingbo.algorithmapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class AlgorithmAppApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello,bill");
        ListNode node = new ListNode(2);
        node.next = new ListNode(8);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(1);
        ListNode node1 = new ListNode(7);
        node1.next = new ListNode(7);
        node1.next.next = new ListNode(9);

        ListNode node2 = multiply(node, node);
        while (node2 != null) {
            System.out.println(node2.element);
            node2 = node2.next;
        }
    }

    /**
     * 两个链表相加
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 相加之后的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return helpler(l1, l2, 0);
    }

    //链表相加帮助类
    public static ListNode helpler(ListNode l1, ListNode l2, int k) {
        if (l1 == null && l2 == null) {
            return k == 0 ? null : new ListNode(k);
        }
        if (l1 == null && l2 != null) {
            l1 = new ListNode(0);
        }
        if (l1 != null && l2 == null) {
            l2 = new ListNode(0);
        }
        int sum = l1.element + l2.element + k;
        ListNode curr = new ListNode(sum % 10);
        curr.next = helpler(l1.next, l2.next, sum / 10);
        return curr;
    }

    /**
     * 链表相乘
     *
     * @param l1 链表一
     * @param l2 链表二
     * @return 相乘之后的链表
     */
    public ListNode multiply(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        ListNode head = new ListNode(0);
        ListNode res = head;
        ListNode result = new ListNode(0);
        while (l2 != null) {
            result = addTwoNumbers(result, multiplyHelpler(l1, l2));
            l2 = l2.next;
            head.next = result;
            result = result.next;
            head = head.next;
        }
        return res.next;
    }

    //链表相乘帮助类
    public static ListNode multiplyHelpler(ListNode l1, ListNode l2) {

        ListNode l1Tem = l1;
        ListNode result = new ListNode(0);
        ListNode res = result;
        while (l1Tem != null) {
            int temp = l1Tem.element * l2.element + result.element;
            result.element = temp % 10;
            int key = temp / 10;
            l1Tem = l1Tem.next;
            if (l1Tem != null) {
                result.next = new ListNode(key);
            }
            result = result.next;
        }
        return res;
    }

    private static class ListNode {

        public int element;
        public ListNode next;

        public ListNode(int element) {
            this.element = element;
        }

    }

}
