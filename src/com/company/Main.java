package com.company;

import com.company.internal.OrderBooks;
import com.company.internal.TradeLedger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please enter the 'add' command to add an order (add GOOG B 100 50), " +
                "'cancel' to cancel the order (cancel GOOG B 100 50), or 'q' to exit the program.");

        iOrderBooks orderBooks = new OrderBooks();
        iTradeLedger tradeLedger = new TradeLedger();
        TradingGateway tradingGateway = new TradingGateway(orderBooks, tradeLedger);
        MatchingEngine matchingEngine = new MatchingEngine(orderBooks, tradeLedger);

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(() -> {
            if (tradingGateway.isQuit()) {
                ses.shutdown();
                tradingGateway.stop();
                matchingEngine.stop();
                System.exit(0);
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
