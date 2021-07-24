package com.company;

import java.util.Collection;

public interface iTradeLedger {
    void add(iTrade trade);

    Collection<iTrade> get();
}
