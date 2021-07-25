package com.company;

import com.company.internal.Trade;
import com.company.internal.TransactionType;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Math.min;

/**
 * "Balances" (like in "balancing the book", i.e. crossing buys and sells) the order books every 1 second.
 */
public class MatchingEngine {

    private final ScheduledExecutorService ses;

    /**
     * Create new Matching Engine object.
     *
     * @param orderBooks contains all orders (buy and sell) for a certain stock.
     * @param tradeLedger contains all trades that happen on all order books.
     */
    public MatchingEngine(iOrderBooks orderBooks, iTradeLedger tradeLedger) {

        this.ses = Executors.newSingleThreadScheduledExecutor();
        this.ses.scheduleAtFixedRate(() -> match(orderBooks, tradeLedger), 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        this.ses.shutdown();
    }

    public void match(iOrderBooks orderBooks, iTradeLedger tradeLedger) {

        for (iOrderBook orderBook : orderBooks.get()) {

            List<iOrder> buys = orderBook.get().stream().filter(o -> o.getType() == TransactionType.B &&
                    tradeLedger.get().stream().noneMatch(t -> t.getOrder(TransactionType.B).getID() == o.getID())).
                    sorted((o1, o2) -> o1.getPrice() > o2.getPrice() ? o1.getPrice().
                            compareTo(o2.getPrice()) : o1.getTime().compareTo(o2.getTime())).collect(Collectors.toList());

            List<iOrder> sells = orderBook.get().stream().filter(o -> o.getType() == TransactionType.S &&
                    tradeLedger.get().stream().noneMatch(t -> t.getOrder(TransactionType.S).getID() == o.getID())).
                    sorted((o1, o2) -> o1.getPrice() > o2.getPrice() ? o1.getPrice().
                            compareTo(o2.getPrice()) : o1.getTime().compareTo(o2.getTime())).collect(Collectors.toList());

            for (iOrder orderBuy : buys) {
                for (iOrder orderSell : sells) {
                    if (orderBuy.getPrice() >= orderSell.getPrice()) {
                        int minQuantity = min(orderBuy.getQuantity(), orderSell.getQuantity());
                        iTrade trade = new Trade(orderBuy, orderSell, minQuantity, orderBuy.getPrice());
                        tradeLedger.add(trade);
                        return;
                    }
                }
            }
        }
    }
}
