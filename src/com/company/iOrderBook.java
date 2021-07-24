package com.company;

import java.util.Collection;

public interface iOrderBook {

    String getSymbol();

    void add(iOrder order);

    void cancel(iOrder order);

    Collection<iOrder> get();
}
