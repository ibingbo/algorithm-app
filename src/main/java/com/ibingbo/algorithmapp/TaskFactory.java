package com.ibingbo.algorithmapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangbingbing
 * @date 2021/1/28
 */
public class TaskFactory {

    static Set<String> record = new HashSet<>();
    static Map<String, Task> taskMap = new ConcurrentHashMap<>();

    static class Task implements Runnable {

        String name;
        String nextTaskName;
        String prevTaskName;

        @Override
        public void run() {
            System.out.println(name);
        }

    }

    static class Node {

        Task task;
        Node next;

        public Node() {
        }

        public Node(Task task) {
            this.task = task;
        }

    }

    /**
     * 添加任务时创建链表，然后判断是否存在循环依赖，如果有返回错误，否则添加所有任务
     *
     * @param tasks
     * @return
     */
    public static boolean addTask(List<Task> tasks) {
        Map<String, Task> map = new HashMap<>();
        tasks.forEach(task -> {
            map.put(task.name, task);
        });
        Task task = tasks.get(0);
        Node head = new Node(task);
        Node cur = head;
        while (cur.task.nextTaskName != null) {
            cur.next = new Node(map.get(cur.task.nextTaskName));
            map.remove(cur.task.nextTaskName);
            cur = cur.next;
        }
        if (hasCycle(head)) {
            return false;
        }
        taskMap.putAll(map);
        return true;
    }

    /**
     * 获取任务，如果前置任务为空或已执行，则记录并返回，否则先返回前置任务执行
     *
     * @param taskName
     * @return
     */
    public static Runnable getTask(String taskName) {
        Task task = taskMap.get(taskName);
        if (task.prevTaskName == null || (record.contains(task.prevTaskName))) {
            record.add(task.name);
            return task;
        }
        return getTask(task.prevTaskName);
    }

    /**
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(Node head) {
        Node walker = head;
        Node runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker.task.name.equals(runner.task.name)) {
                return true;
            }
        }
        return false;
    }

}
