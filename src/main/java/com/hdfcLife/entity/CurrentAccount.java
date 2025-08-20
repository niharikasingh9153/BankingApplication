package com.hdfcLife.entity;

import com.hdfcLife.enums.AccountType;

import java.math.BigDecimal;

public class CurrentAccount extends Account{

    public static final BigDecimal intrest_rate = new BigDecimal(4);
    public static final BigDecimal minimum_balance=new BigDecimal(0);

    public  CurrentAccount(){
        super();
        setType(AccountType.CURRENT);
    }

    public CurrentAccount(String accountNo, String customerId, AccountType type, BigDecimal balance) {
        super(accountNo, customerId, AccountType.CURRENT, balance);
    }

    @Override

    public BigDecimal getIntrestRate(){
        return intrest_rate;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return minimum_balance;
    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "accountNo='" + getAccountNo() + '\'' +
                ", customerId='" + getCustomerId() + '\'' +
                ", balance=" + getBalance() +
                '}';
    }
}
