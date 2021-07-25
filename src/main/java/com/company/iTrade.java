package com.company;

import com.company.internal.TransactionType;

import java.time.OffsetDateTime;

public interface iTrade {
    OffsetDateTime getTime();

    int getID();

    iOrder getOrder(TransactionType type);

    int getQuantity();

    int getPrice();
}
