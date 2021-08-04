package com.company.internal;

import com.company.iOrder;

import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Order implements iOrder {

    private static AtomicInteger lastID = new AtomicInteger(0);
    private final String stock;
    private final TransactionType type;
    private final int quantity;
    private final Integer price;
    private final OffsetDateTime time;
    private final int ID;

    public Order(String stock, TransactionType type, int quantity, int price) {
        this.stock = stock;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.time = OffsetDateTime.now();
        this.ID = lastID.incrementAndGet();
    }

    @Override
    public TransactionType getType() {
        return this.type;
    }

    @Override
    public String getStock() {
        return this.stock;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public OffsetDateTime getTime() {
        return this.time;
    }

    @Override
    public int getID() {
        return this.ID;
    }

}
