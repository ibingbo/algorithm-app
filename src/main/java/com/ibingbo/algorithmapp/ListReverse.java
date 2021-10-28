package com.ibingbo.algorithmapp;

/**
 * @author zhangbingbing
 * @date 2020/12/16
 */
public class ListReverse {

    public static void main(String[] args) {
        Node root = new Node();
        Node tmp = root;
        int[] arr = {1, 2, 3, 4, 5, 6};
        for (int i = 0; i < arr.length; i++) {
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node();
            tmp.next.value = arr[i];
        }
        tmp = root;
        while (tmp.next != null) {
            System.out.println(tmp.next.value);
            tmp = tmp.next;
        }
        System.out.println("----------");
        root = reverse(root.next);
        tmp = root;
        while (tmp.next != null) {
            System.out.println(tmp.next.value);
            tmp = tmp.next;
        }

    }

    static class Node {

        public int value;
        public Node next;

    }

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

}
