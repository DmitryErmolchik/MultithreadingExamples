package com.dim4tech.accounts.domain;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int money;
    private Lock lock = new ReentrantLock();

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void outCome(int value) {
        money -= value;
    }

    public void inCome(int value) {
        money += value;
    }

    public boolean isEnoughtMoneyToTransfer(int value) {
        return money - value > 0;
    }

    public boolean getLock() {
        try {
            return lock.tryLock(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("Getting of Account Lock was INTERRUPTED");
            return false;
        }
    }

    public void unlock() {
        lock.unlock();
    }
}
