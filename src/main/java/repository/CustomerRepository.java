package repository;

import model.BankTableData;
import service.BankFileService;

public class CustomerRepository implements Repository {
    private static CustomerRepository INSTANCE;
    private CustomerRepository(){}
    public static CustomerRepository getInstance(){
        if( INSTANCE == null )
            INSTANCE = new CustomerRepository();
        return INSTANCE;
    }

    @Override
    public void save(BankTableData row) {
        System.out.println("SAVING:\t" + row);
    }
}
