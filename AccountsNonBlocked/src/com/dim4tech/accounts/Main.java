package com.dim4tech.accounts;

import com.dim4tech.accounts.domain.Account;
import com.dim4tech.accounts.domain.Order;
import com.dim4tech.accounts.service.Worker;
import com.dim4tech.accounts.service.ordersgenerator.OrdersGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private final static int TRANSFERERS_NUMBER = 8;
    private final static int ACOUNTS_NUMBER = 12;
    private final static int QUEUE_LENGTH = 1000;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        BlockingQueue<Order> orders = new LinkedBlockingQueue<>(QUEUE_LENGTH);

        List<Account> accounts = new ArrayList<>();
        for (int i=0; i<ACOUNTS_NUMBER; i++) {
            accounts.add(new Account(100));
        }

        OrdersGenerator ordersGenerator = new OrdersGenerator(accounts, orders);
        executorService.submit(ordersGenerator);

        for (int i = 0; i<TRANSFERERS_NUMBER; i++) {
            Worker worker = new Worker(orders);
            executorService.submit(worker);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (Account account : accounts) {
            sum += account.getMoney();
        }
        System.out.println(sum);
    }
}
