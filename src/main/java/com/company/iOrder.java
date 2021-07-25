package com.company;

import com.company.internal.TransactionType;

import java.time.OffsetDateTime;

public interface iOrder {

    TransactionType getType();

    String getStock();

    Integer getPrice();

    int getQuantity();

    OffsetDateTime getTime();

    int getID();

}
