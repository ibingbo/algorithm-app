package com.ibingbo.algorithmapp;

/**
 * 有两个单链表，链表每个节点有一个0-9的数字，整个链表可以看做一个大整数，编程实现两个链表的乘法，返回一个新的链表包含乘积（注意链表长度可能会较长，直接将链表转数字进行乘法可能会溢出）。
 * 输入：  1 -> 3 -> 8 -> 2
 * 9 -> 7 -> 7
 * 输出：  1 -> 3 -> 5 -> 0 -> 2 -> 1 -> 4      (1382 x 977 = 1350214)
 *
 * @author zhangbingbing
 * @date 2021/1/28
 */
public class TwoListMulti {

    static class Node {

        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        Node prev = null;
        Node cur = head;
        while (cur != null) {
            Node tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }

    /**
     * 两个链表相乘
     * 1、先进行反转
     * 2、结合10的几次幂分别运算
     * 3、累加结果
     * 4、根据整数结果转化为链表
     * 5、再对创建的链表进行反转
     *
     * @param node1
     * @param node2
     * @return
     */
    static Node multi(Node node1, Node node2) {
        Node newNode1 = reverse(node1);
        Node newNode2 = reverse(node2);
        Node cur1 = newNode1;
        Node cur2 = newNode2;
        int i = 0;
        int result = 0;
        while (cur1 != null) {
            int j = 0;
            while (cur2 != null) {
                result += cur1.value * (int) Math.pow(10, i) * cur2.value * (int) Math.pow(10, j);
                cur2 = cur2.next;
                j++;
            }
            cur1 = cur1.next;
            i++;
            cur2 = newNode2;
        }
        Node tmp = createList(result);
        return reverse(tmp);
    }

    /**
     * 根据整数串创建链表
     *
     * @param result
     * @return
     */
    static Node createList(int result) {
        Node head = new Node(0);
        Node cur = head;
        while (result != 0) {
            int val = result % 10;
            cur.next = new Node(val);
            cur = cur.next;
            result = result / 10;
        }
        return head.next;
    }

    /**
     * 输出链表
     *
     * @param node
     */
    static void display(Node node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 初始链表1 3 8 2
        Node node1 = new Node(1);
        node1.next = new Node(3);
        node1.next.next = new Node(8);
        node1.next.next.next = new Node(2);
        // 初始链表9 7 7
        Node node2 = new Node(9);
        node2.next = new Node(7);
        node2.next.next = new Node(7);
        // 两链表相乘
        Node result = multi(node1, node2);
        display(result);
    }

}
