package com.dim4tech.accounts.service.transferer;

import com.dim4tech.accounts.domain.Order;

public class Transferer {

    public boolean transfer(Order order) {
        int srcMoney = order.getAccountSrc().getMoney();
        if (srcMoney - order.getValue() > 0) {
            System.out.println(order.getAccountSrc() + " have enought money");
            if (order.getAccountSrc().outCome(srcMoney, order.getValue())) {
                order.getAccountDst().inCome(order.getValue());
            } else {
                return false;
            }
        } else {
            System.out.println(order.getAccountSrc() + " have NOT enought money");
            return false;
        }
        return true;
    }
}
