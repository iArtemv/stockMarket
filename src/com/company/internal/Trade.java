package com.company.internal;

import com.company.iOrder;
import com.company.iTrade;

import java.time.OffsetDateTime;

public class Trade implements iTrade {

    private static int lastID = 0;
    private final iOrder orderBuy;
    private final iOrder orderSell;
    private final int quantity;
    private final int price;
    private final OffsetDateTime time;
    private final int ID;

    public Trade(iOrder orderBuy, iOrder orderSell, int quantity, int price) {
        this.orderBuy = orderBuy;
        this.orderSell = orderSell;
        this.price = price;
        this.quantity = quantity;
        this.time = OffsetDateTime.now();
        this.ID = ++lastID;
    }

    @Override
    public OffsetDateTime getTime() {
        return this.time;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public iOrder getOrder(TransactionType type) {
        if (type == TransactionType.S)
            return this.orderSell;
        if (type == TransactionType.B)
            return this.orderBuy;
        return null;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
