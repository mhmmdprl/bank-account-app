package entity;

public class TLAccount extends Account {

    public TLAccount(Double balance, String accountName, Integer accountNo) {
        super(balance, accountName, accountNo);
    }

    @Override
    public void calculateBalance() {
        getIncomingOutgoingAccountList().forEach(transaction -> {
            if (transaction.getSendingAccount().getAccountNo().equals(getAccountNo())) {
              setBalance(getBalance() - transaction.getAmount());
            } else if(transaction.getBuyerAccount().getAccountNo().equals(getAccountNo())) {
                setBalance(getBalance() + transaction.getAmount());
            }
        });
    }
}
