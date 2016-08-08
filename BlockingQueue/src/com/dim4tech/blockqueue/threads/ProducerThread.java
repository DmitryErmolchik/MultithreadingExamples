package com.dim4tech.blockqueue.threads;

import com.dim4tech.blockqueue.queue.BlockingQueue;

public class ProducerThread implements Runnable {
    private final BlockingQueue blockingQueue;

    public ProducerThread(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                blockingQueue.set(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
