package com.ibingbo.algorithmapp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangbingbing
 * @date 2020/12/23
 */
public class BinaryTree {

    static class Node {

        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

    }

    public static void main(String[] args) {
        Node root = initBinaryTree();
        System.out.println("--------pre order------");
        preOrder(root);
        System.out.println("--------pre order1------");
        preOrder1(root);
        System.out.println("-------post order-------");
        postOrder(root);
        System.out.println("-------post order1-------");
        postOrder1(root);
        System.out.println("---------in order-----");
        inOrder(root);
        System.out.println("---------in order1-----");
        inOrder1(root);
        System.out.println("-------level order-------");
        levelOrder(root);
        System.out.println("--------switch left right------");
        switchLeftRight1(root);
        levelOrder(root);
        System.out.println("---------get max-------");
        System.out.println(getMax(root));
        System.out.println("---------get height-----");
        System.out.println(getHeight(root));
    }

    /**
     * 获取树的高度
     *
     * @param root
     * @return
     */
    public static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left > right) {
            return left + 1;
        }
        return right + 1;
    }

    /**
     * 查找树中最大值
     *
     * @param root
     * @return
     */
    public static int getMax(Node root) {
        if (root == null) {
            return -1;
        }
        int max = root.data;
        int left = getMax(root.left);
        int right = getMax(root.right);
        if (max < left) {
            max = left;
        }
        if (max < right) {
            max = right;
        }
        return max;
    }

    /**
     * 递归前序遍历
     * 先访问根节点
     * 再序遍历左子树
     * 最后序遍历右子树
     *
     * @param root
     */
    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 循环遍历前序遍历
     * 首先申请一个新的栈，记为stack；
     * 将头结点head压入stack中；
     * 每次从stack中弹出栈顶节点，记为cur，然后打印cur值，如果cur右孩子不为空，则将右孩子压入栈中；如果cur的左孩子不为空，将其压入stack中；
     * 重复步骤3，直到stack为空.
     *
     * @param root
     */
    public static void preOrder1(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.data);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }

        }
    }

    /**
     * 递归后序遍历
     * 先后序遍历左子树
     * 再后序遍历右子树
     * 最后访问根节点
     *
     * @param root
     */
    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    /**
     * 循环后序遍历
     * 申请一个栈stack，将头节点压入stack，同时设置两个变量 h 和 c，在整个流程中，h代表最近一次弹出并打印的节点，c代表当前stack的栈顶节点，初始时令h为头节点，，c为null；
     * <p>
     * 每次令c等于当前stack的栈顶节点，但是不从stack中弹出节点，此时分一下三种情况：
     * <p>
     * (1)如果c的左孩子不为空，并且h不等于c的左孩子，也不等于c的右孩子，则吧c的左孩子压入stack中
     * <p>
     * (2)如果情况1不成立，并且c的右孩子不为空，并且h不等于c的右孩子，则把c的右孩子压入stack中；
     * <p>
     * (3)如果情况1和2不成立，则从stack中弹出c并打印，然后令h等于c；
     * <p>
     * 一直重复步骤2，直到stack为空.
     *
     * @param root
     */
    public static void postOrder1(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        Node head = root;
        Node cur;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left != null && cur.left != head && cur.right != head) {
                stack.add(cur.left);
            } else if (cur.right != null && cur.right != head) {
                stack.add(cur.right);
            } else {
                Node node = stack.pop();
                System.out.println(node.data);
                head = cur;
            }
        }
    }

    /**
     * 递归中序遍历
     * 先中序遍历左子树
     * 再访问根节点
     * 最后中序遍历右子树
     *
     * @param root
     */
    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    /**
     * 非递归中序遍历
     * 申请一个新栈，记为stack，申请一个变量cur，初始时令cur为头节点；
     * 先把cur节点压入栈中，对以cur节点为头的整棵子树来说，依次把整棵树的左子树压入栈中，即不断令cur=cur.left，然后重复步骤2；
     * 不断重复步骤2，直到发现cur为空，此时从stack中弹出一个节点记为node，打印node的值，并让cur = node.right，然后继续重复步骤2；
     * 当stack为空并且cur为空时结束。
     *
     * @param root
     */
    public static void inOrder1(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            Node node = stack.pop();
            System.out.println(node.data);
            cur = node.right;
        }
    }

    /**
     * 层次广度优先遍历
     * 首先申请一个新的队列，记为queue；
     * 将头结点head压入queue中；
     * 每次从queue中出队，记为node，然后打印node值，如果node左孩子不为空，则将左孩子入队；如果node的右孩子不为空，则将右孩子入队；
     * 重复步骤3，直到queue为空。
     *
     * @param root
     */
    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.data);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 循环交换二叉树的左右子树
     * 使用队列先进先出，初始添加根节点，
     * 循环拿出一个节点，交换左右子树
     * 然后判断左子树不为空放入队列，右子树不为空放入队列
     * 继续循环
     *
     * @param root
     */
    public static void switchLeftRight1(Node root) {
        if (root != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                Node tmp = node.left;
                node.left = node.right;
                node.right = tmp;
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
    }

    /**
     * 递归交换二叉树的左右子树
     *
     * @param root
     */
    public static void switchLeftRight(Node root) {
        if (root == null) {
            return;
        }
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        switchLeftRight1(root.left);
        switchLeftRight1(root.right);
    }

    /**
     * 初始化二叉树
     * **********1
     * ****2           3
     * 5       4   7       6
     *
     * @return
     */
    public static Node initBinaryTree() {
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        root.left.right = new Node(5);
        root.left.left = new Node(4);
        root.right.right = new Node(7);
        root.right.left = new Node(6);
        return root;
    }

}
