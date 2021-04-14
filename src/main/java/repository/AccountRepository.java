package repository;

import model.BankTableData;

public class AccountRepository implements Repository {
    private static AccountRepository INSTANCE;
    private AccountRepository(){}
    public static AccountRepository getInstance(){
        if( INSTANCE == null )
            INSTANCE = new AccountRepository();
        return INSTANCE;
    }

    @Override
    public void save(BankTableData row){
        System.out.println("SAVING:\t" + row);
    }
}
