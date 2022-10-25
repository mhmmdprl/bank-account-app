import entity.Account;
import entity.DollarAccount;
import entity.EuroAccount;
import entity.TLAccount;
import entity.Transaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import service.ReportService;

public class Main {
    static Map<Integer, Account> accountMap = new HashMap<>();

    public static void main(String[] args) {

        List<Transaction> transactionList;
        String regex = ";";
        try {
            BufferedReader brAccount = new BufferedReader(new FileReader("account.csv"));
            BufferedReader brTransaction = new BufferedReader(new FileReader("transaction.csv"));
            accountMap = brAccount.lines().skip(1).collect(Collectors.toMap(line -> {
                String[] x = line.split(regex);
                Integer accountNo = Integer.parseInt(x[0]);
                return accountNo;
            }, line -> {
                String[] x = line.split(regex);
                Integer accountNo = Integer.parseInt(x[0]);
                String accountName = x[1];
                String moneyUnit = x[2];
                Double balance = Double.parseDouble(x[3]);
                switch (moneyUnit) {
                    case "dollar":
                        return new DollarAccount(balance, accountName, accountNo);
                    case "euro":
                        return new EuroAccount(balance, accountName, accountNo);
                    default:
                        return new TLAccount(balance, accountName, accountNo);
                }
            }));

            transactionList = brTransaction.lines().skip(1).map(line -> {
                String[] x = line.split(regex);
                Integer senderAccountNo = Integer.parseInt(x[0]);
                Integer buyerAccountNo = Integer.parseInt(x[1]);
                Double balance = Double.parseDouble(x[2]);
                Account senderAccount = accountMap.get(senderAccountNo);
                Account buyerAccount = accountMap.get(buyerAccountNo);
                senderAccount.getIncomingOutgoingAccountList().add(new Transaction(senderAccount, buyerAccount, balance));
                buyerAccount.getIncomingOutgoingAccountList().add(new Transaction(senderAccount, buyerAccount, balance));
                return new Transaction(senderAccount, buyerAccount, balance);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, Account> entry : accountMap.entrySet()) {
            entry.getValue().calculateBalance();
        }

        List<Account> accountList = accountMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        ReportService reportService = new ReportService();
        reportService.writeAsPdf(accountList);
        System.out.println("bitti");
    }
}




