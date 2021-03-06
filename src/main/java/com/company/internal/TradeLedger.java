package com.company.internal;

import com.company.iTrade;
import com.company.iTradeLedger;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class TradeLedger implements iTradeLedger {

    private final CopyOnWriteArrayList<iTrade> trades;

    public TradeLedger() {
        this.trades = new CopyOnWriteArrayList<>();
    }

    @Override
    public void add(iTrade trade) {

        this.trades.add(trade);

        System.out.printf("[%s] New execution with ID %s: %s %s @ %s (order %s and %s)%s", trade.getTime(),
                trade.getID(), trade.getOrder(TransactionType.B).getStock(), trade.getQuantity(), trade.getPrice(),
                trade.getOrder(TransactionType.B).getID(), trade.getOrder(TransactionType.S).getID(), System.lineSeparator());
    }

    @Override
    public Collection<iTrade> get() {
        return this.trades;
    }
}
