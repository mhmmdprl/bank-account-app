package entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    Double balance;
    List<Transaction> incomingOutgoingAccountList = new ArrayList<>();;
    String accountName;
    Integer accountNo;

    public Account(Double balance, String accountName, Integer accountNo) {
        this.balance = balance;
        this.accountName = accountName;
        this.accountNo = accountNo;
    }

    public abstract void calculateBalance();

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getIncomingOutgoingAccountList() {
        return incomingOutgoingAccountList;
    }

    public void setIncomingOutgoingAccountList(List<Transaction> incomingOutgoingAccountList) {
        this.incomingOutgoingAccountList = incomingOutgoingAccountList;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }


}
