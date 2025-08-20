package com.hdfcLife.entity;

import com.hdfcLife.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private String transactionId;
    private String fromAccountNo;
    private String toAccountNo;
    private BigDecimal amount;
    private String accountNo;
    private LocalDate timestamp;
    private TransactionType type;


    public Transaction(){}

    public Transaction(String transactionId, String fromAccountNo, String toAccountNo, BigDecimal amount, LocalDate timestamp, TransactionType type) {
        this.transactionId = transactionId;
        this.fromAccountNo = fromAccountNo;
        this.toAccountNo = toAccountNo;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Transaction(String transactionId, BigDecimal amount, String accountNo, LocalDate timestamp, TransactionType type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.accountNo = accountNo;
        this.timestamp = timestamp;
        this.type = type;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromAccountNo() {
        return fromAccountNo;
    }

    public void setFromAccountNo(String fromAccountNo) {
        this.fromAccountNo = fromAccountNo;
    }

    public String getToAccountNo() {
        return toAccountNo;
    }

    public void setToAccountNo(String toAccountNo) {
        this.toAccountNo = toAccountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String amountNo) {
        this.accountNo = accountNo;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", amountNo='" + accountNo + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }

    @Override
    public int hashCode(){
        return Objects.hash(transactionId);
    }

    @Override

    public boolean equals(Object obj){
        if(obj==null){
            return false;

        }
        if(this==obj){
            return true;
        }
        Transaction transaction=(Transaction) obj;
        return Objects.equals(transactionId,transaction.transactionId);
    }
}
