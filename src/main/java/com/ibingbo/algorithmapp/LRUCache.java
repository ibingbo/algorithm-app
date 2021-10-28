package com.ibingbo.algorithmapp;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存类设计
 * 通过hash+双链表的形式实现
 * 访问的和插入的元素放在链表头部，长期未访问的会在尾部，容量超了直接从尾部删除
 *
 * @author zhangbingbing
 * @date 2021/1/28
 */
public class LRUCache {

    static class Node {

        int key;
        int value;
        Node next;
        Node prev;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    private Map<Integer, Node> cache = new HashMap<>();
    private int size;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();
    }

    /**
     * 添加
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            // 不存在新建节点
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                // 超出容量，删除链表的尾部节点
                Node tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            // 存在由更新并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 查询
     *
     * @param key
     * @return
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 删除
     *
     * @param key
     */
    public void remove(int key) {
        Node node = cache.get(key);
        if (node != null) {
            removeNode(node);
        }
    }

    /**
     * 删除指定节点
     *
     * @param node
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 添加节点到链表头部
     *
     * @param node
     */
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 移动节点到链表头部
     *
     * @param node
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 删除链表尾部结点
     *
     * @return
     */
    private Node removeTail() {
        Node tmp = tail.prev;
        removeNode(tmp);
        return tmp;
    }

}
