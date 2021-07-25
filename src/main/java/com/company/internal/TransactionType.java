package com.company.internal;

public enum TransactionType {
    B("Buy"),
    S("Sell");

    private final String title;

    TransactionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
