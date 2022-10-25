package service;

import entity.Account;
import entity.Transaction;
import java.util.ArrayList;
import java.util.List;

public class FileImportService {

    public List<Account> hesaplariOku(String fileName){
        //dosyadaki satırlar obje listesine çevirilecek.
        return new ArrayList<Account>();
    }

    public List<Transaction> hareketleriOku(String fileName){
        //dosyadaki satırlar obje listesine çevirilecek.
        return new ArrayList<Transaction>();
    }
}
