package com.awbd.enums;

public enum TransactionTypeEnum {
    SPENT ("SPENT"),
    EARNED ("EARNED");

    private String description;

    public String getDescription() {
        return description;
    }

    TransactionTypeEnum(String description) {
        this.description = description;
    }
}
