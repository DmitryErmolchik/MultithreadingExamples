package com.dim4tech.accounts.service.transferer;

import com.dim4tech.accounts.domain.Order;

public class Transferer {

    public boolean transfer(Order order) {
        if (order.getAccountSrc().getLock()) {
            try {
                System.out.println(order.getAccountSrc() + " locked. Money: " + order.getAccountSrc().getMoney());
                if (order.getAccountSrc().isEnoughtMoneyToTransfer(order.getValue())) {
                    System.out.println(order.getAccountSrc() + " have enought money");
                    if (order.getAccountDst().getLock()) {
                        try {
                            System.out.println(order.getAccountDst() + " locked");
                            order.getAccountSrc().outCome(order.getValue());
                            order.getAccountDst().inCome(order.getValue());
                            System.out.println(order.getAccountSrc() + " money: " + order.getAccountSrc().getMoney() + "  " + order.getAccountDst() + " money: " + order.getAccountDst().getMoney());
                        } finally {
                            order.getAccountDst().unlock();
                            System.out.println(order.getAccountDst() + " UNlocked");
                        }
                    } else {
                        System.out.println(order.getAccountDst() + " NOT locked");
                        return false;
                    }
                } else {
                    System.out.println(order.getAccountSrc() + " have NOT enought money");
                    return false;
                }
                return true;
            }
            finally {
                order.getAccountSrc().unlock();
            }
        }
        return false;
    }
}
