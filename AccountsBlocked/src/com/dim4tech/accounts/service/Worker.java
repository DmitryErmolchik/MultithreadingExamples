package com.dim4tech.accounts.service;

import com.dim4tech.accounts.domain.Order;
import com.dim4tech.accounts.service.transferer.Transferer;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {
    private final Transferer transferer = new Transferer();
    private final BlockingQueue<Order> orders;

    public Worker(BlockingQueue<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        try {
            while (!orders.isEmpty()) {
                Order order = orders.take();
                if (!transferer.transfer(order)) {
                    System.out.println("Transagtion for " + order + " FAILED");
                } else {
                    System.out.println("Transagtion for " + order + " COMPLETED");
                }
                System.out.println("Orders size: " + orders.size());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
