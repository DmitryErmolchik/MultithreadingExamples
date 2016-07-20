package com.dim4tech.accounts.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private AtomicInteger money = new AtomicInteger();

    public Account(int money) {
        this.money.set(money);
    }

    public int getMoney() {
        return money.get();
    }

    public boolean outCome(int expectedBalance, int value) {
        return money.compareAndSet(expectedBalance, expectedBalance -value);
    }

    public void inCome(int value) {
        money.addAndGet(value);
    }
}
