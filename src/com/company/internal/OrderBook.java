package com.company.internal;

import com.company.iOrder;
import com.company.iOrderBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class OrderBook implements iOrderBook {

    private final Collection<iOrder> orders;
    private final String symbol;

    public OrderBook(String symbol) {

        this.orders = new ArrayList<>();
        this.symbol = symbol.toUpperCase(Locale.ROOT);

    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void add(iOrder order) {

        this.orders.add(order);

        System.out.printf("[%s] Order with ID %s %sed: %s %s %s @ %s%s", order.getTime(), order.getID(), Command.ADD.getTitle(),
                order.getStock(), order.getType().getTitle(), order.getQuantity(), order.getPrice(), System.lineSeparator());
    }

    @Override
    public void cancel(iOrder order) {

        this.orders.remove(order);

        System.out.printf("[%s] Order with ID %s %sed: %s %s %s @ %s%s", order.getTime(), order.getID(), Command.CANCEL.getTitle(),
                order.getStock(), order.getType().getTitle(), order.getQuantity(), order.getPrice(), System.lineSeparator());
    }

    @Override
    public Collection<iOrder> get() {
        return orders;
    }
}
