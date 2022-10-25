package entity;

public class Transaction {

    private Account sendingAccount;
    private Account buyerAccount;
    Double amount;

    public Transaction(Account sendingAccount, Account buyerAccount, Double amount) {
        this.sendingAccount = sendingAccount;
        this.buyerAccount = buyerAccount;
        this.amount = amount;
    }

    public Account getSendingAccount() {
        return sendingAccount;
    }

    public void setSendingAccount(Account sendingAccount) {
        this.sendingAccount = sendingAccount;
    }

    public Account getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(Account buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
