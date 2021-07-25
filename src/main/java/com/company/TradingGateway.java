package com.company;

import com.company.internal.Command;
import com.company.internal.Order;
import com.company.internal.TransactionType;

import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Object allows a client to trade on the market.
 *
 */
public class TradingGateway {

    private final iOrderBooks orderBooks;
    private final iTradeLedger tradeLedger;
    private final ScheduledExecutorService ses;
    private boolean isQuit;

    /**
     * Create new Trading Gateway object.
     *
     * @param orderBooks contains all orders (buy and sell) for a certain stock.
     * @param tradeLedger contains all trades that happen on all order books.
     */
    public TradingGateway(iOrderBooks orderBooks, iTradeLedger tradeLedger) {

        this.orderBooks = orderBooks;
        this.tradeLedger = tradeLedger;
        this.ses = Executors.newSingleThreadScheduledExecutor();
        this.ses.scheduleAtFixedRate(this::scanCommand, 0, 3, TimeUnit.SECONDS);

    }

    protected void stop() {
        this.ses.shutdown();
    }

    protected boolean isQuit() {
        return this.isQuit;
    }

    private void scanCommand() {
        Scanner in = new Scanner(System.in);
        this.isQuit = in.hasNext("q");
        if (in.hasNext()) {
            String next = in.next();
            Command command;
            try {
                command = Command.valueOf(next.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid value for type " + next);
            }
            if (in.hasNext()) {
                String stock = in.next();
                if (in.hasNext()) {
                    next = in.next();
                    TransactionType type;
                    try {
                        type = TransactionType.valueOf(next.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Invalid value for type " + next);
                    }
                    if (in.hasNextInt()) {
                        int quantity = in.nextInt();
                        if (in.hasNextInt()) {
                            int price = in.nextInt();

                            if (Command.ADD.equals(command))
                                addOrder(stock, type, quantity, price);

                            if (Command.CANCEL.equals(command))
                                cancelOrder(stock, type, quantity, price);

                        }
                    }
                }
            }
        }
    }

    /**
     * Add order.
     *
     * @param stock stock
     * @param type type
     * @param quantity quantity
     * @param price price
     */
    public void addOrder(String stock, TransactionType type, int quantity, int price) {
        iOrderBook orderBook = orderBooks.getOrderBook(stock);
        iOrder order = new Order(stock, type, quantity, price);
        orderBook.add(order);
    }

    /**
     * Cancel order.
     *
     * @param stock stock
     * @param type type
     * @param quantity quantity
     * @param price price
     */
    public void cancelOrder(String stock, TransactionType type, int quantity, int price) {
        iOrderBook orderBook = orderBooks.getOrderBook(stock);
        orderBook.get().stream().filter(o -> tradeLedger.get().stream().noneMatch(t -> t.getOrder(o.getType()).getID() == o.getID())
                && o.getStock().equalsIgnoreCase(stock) && o.getType().equals(type)
                && o.getQuantity() == quantity && o.getPrice().equals(price)).findFirst().ifPresent(orderBook::cancel);
    }
}
