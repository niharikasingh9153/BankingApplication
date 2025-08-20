package com.hdfcLife.enums;

public enum TransactionType {

    DEPOSIT("Deposit Amount"),
    WITHDRAW("Withdraw amount"),
    TRANSFER("TRANSFER AMOUNT");
    private final String displayName;

      TransactionType(String displayName){
        this.displayName=displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}




