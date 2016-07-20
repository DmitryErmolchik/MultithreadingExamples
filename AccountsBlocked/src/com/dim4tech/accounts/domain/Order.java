package com.dim4tech.accounts.domain;

public class Order {
    private final Account accountSrc;
    private final Account accountDst;
    private final int value;

    public Order(Account accountSrc, Account accountDst, int value) {
        this.accountSrc = accountSrc;
        this.accountDst = accountDst;
        this.value = value;
    }

    public Account getAccountSrc() {
        return accountSrc;
    }

    public Account getAccountDst() {
        return accountDst;
    }

    public int getValue() {
        return value;
    }
}
