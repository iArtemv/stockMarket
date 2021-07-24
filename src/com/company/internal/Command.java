package com.company.internal;

public enum Command {
    ADD("add"),
    CANCEL("cancel");

    private final String title;

    Command(String title) {
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
