package com.dim4tech.blockqueue.queue;

import java.util.Deque;
import java.util.LinkedList;

public class BlockingQueue {
    private final static int MAX_SIZE = 5;
    private final Deque<Integer> queue = new LinkedList<>();

    public synchronized void set(Integer integer) throws InterruptedException {
        while (queue.size() >= MAX_SIZE) {
            System.out.println("Setter: Queue is FULL");
            wait();
        }
        queue.addLast(integer);
        System.out.println("Setter: Queue size is " + queue.size());
        notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        while (queue.size() <= 0) {
            System.out.println("Getter: Queue is EMPTY");
            wait();
        }
        Integer value = queue.pollLast();
        System.out.println("Getter: Queue size is " + queue.size());
        notifyAll();
        return value;
    }
}
