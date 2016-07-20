package com.dim4tech.accounts.service.ordersgenerator;

import com.dim4tech.accounts.domain.Account;
import com.dim4tech.accounts.domain.Order;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class OrdersGenerator implements Runnable {
    private final BlockingQueue<Order> orders;
    private final List<Account> accounts;
    private final Random randomAccount;
    private final Random randomValue = new Random(1L);

    public OrdersGenerator(List<Account> accounts, BlockingQueue<Order> orders) {
        this.accounts = accounts;
        this.orders = orders;
        this.randomAccount = new Random(accounts.size());
    }

    @Override
    public void run() {
        int size = accounts.size();
        try {
            while (!Thread.currentThread().interrupted()) {
                int acc1 = randomAccount.nextInt(size);
                int acc2 = randomAccount.nextInt(size);
                while (acc2 == acc1) {
                    acc2 = randomAccount.nextInt(size);
                }
                int value = Math.round(randomValue.nextFloat()) * 30;
                orders.put(new Order(accounts.get(acc1), accounts.get(acc2), value));
            }
        }
        catch (InterruptedException e) {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("OrdersGenerator - STOPPED");
        }
    }
}
