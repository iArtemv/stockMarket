package com.company.internal;

import com.company.iOrderBook;
import com.company.iOrderBooks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class OrderBooks implements iOrderBooks {

    final Collection<iOrderBook> orderBooks;

    public OrderBooks() {
        this.orderBooks = new ArrayList<>();
    }

    @Override
    public iOrderBook getOrderBook(String symbol) {
        iOrderBook orderBook = this.orderBooks.stream().filter(o -> o.getSymbol().equals(symbol.toUpperCase(Locale.ROOT))).findFirst().orElse(null);
        if (orderBook == null) {
            orderBook = new OrderBook(symbol);
            this.orderBooks.add(orderBook);
        }
        return orderBook;
    }

    @Override
    public Collection<iOrderBook> get() {
        return this.orderBooks;
    }
}
