package com.hdfcLife.entity;

import com.hdfcLife.enums.AccountType;

import java.math.BigDecimal;

public class SavingsAccount extends Account{

    public static final BigDecimal intrest_rate = new BigDecimal(4);
    public static final BigDecimal minimum_balance=new BigDecimal(0);

    public SavingsAccount(){
        super();
        setType(AccountType.SAVINGS);

    }

    public SavingsAccount(String accountNo, String customerId, AccountType type, BigDecimal balance) {
        super(accountNo, customerId, AccountType.SAVINGS, balance);
    }

    @Override
    public BigDecimal getIntrestRate() {
        return null;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + getAccountNo() + '\'' +
                ", customerId='" + getCustomerId() + '\'' +
                ", balance=" + getBalance() +
                '}';
    }

}
