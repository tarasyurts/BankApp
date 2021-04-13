package service;

import exception.BankFileException;

import java.util.ArrayList;
import java.util.List;

public class BankFileExceptionsService {

    private static BankFileExceptionsService INSTANCE;
    private BankFileExceptionsService(){ }
    public static BankFileExceptionsService getInstance(){
        if( INSTANCE == null )
            INSTANCE = new BankFileExceptionsService();
        return INSTANCE;
    }

    private final List<BankFileException> bankFileExceptions = new ArrayList<>();

    public void add(BankFileException bankFileException){
        bankFileExceptions.add(bankFileException);
    }

    public List<BankFileException> getBankFileExceptions() {
        return bankFileExceptions;
    }
}
