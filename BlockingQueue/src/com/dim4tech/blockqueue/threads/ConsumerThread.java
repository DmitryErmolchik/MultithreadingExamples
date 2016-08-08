package com.dim4tech.blockqueue.threads;

import com.dim4tech.blockqueue.queue.BlockingQueue;

public class ConsumerThread implements Runnable {
    private final BlockingQueue blockingQueue;

    public ConsumerThread(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                blockingQueue.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
