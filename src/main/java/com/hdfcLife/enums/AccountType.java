package com.hdfcLife.enums;

public enum AccountType {

    SAVINGS("SAVINGS ACCOUNT"),
    CURRENT("CURRENT ACCOUNT");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName(){
        return displayName;
    }
}
