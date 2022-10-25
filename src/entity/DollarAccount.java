package entity;

import enums.ExchangeRate;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class DollarAccount extends Account {


    public DollarAccount(Double balance, String accountName, Integer accountNo) {
        super(balance, accountName, accountNo);
    }

    @Override
    public void calculateBalance() {
        getIncomingOutgoingAccountList().forEach(transaction -> {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            try {
                Number number = format.parse(new DecimalFormat("##.###").format(transaction.getAmount() / ExchangeRate.DOLLAR));
                Double result = number.doubleValue();
                if (transaction.getSendingAccount().getAccountNo().equals(getAccountNo())) {
                    setBalance(getBalance() - result);
                } else if (transaction.getBuyerAccount().getAccountNo().equals(getAccountNo())) {
                    setBalance(getBalance() + result);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
    }
}
