package com.dim4tech.blockqueue;

import com.dim4tech.blockqueue.queue.BlockingQueue;
import com.dim4tech.blockqueue.threads.ConsumerThread;
import com.dim4tech.blockqueue.threads.ProducerThread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new BlockingQueue();
        Thread producer = new Thread(new ProducerThread(blockingQueue));
        Thread consumer = new Thread(new ConsumerThread(blockingQueue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
