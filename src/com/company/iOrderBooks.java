package com.company;

import java.util.Collection;

public interface iOrderBooks {

    iOrderBook getOrderBook(String symbol);

    Collection<iOrderBook> get();

}
