package config;

import service.StringProcessor;
import model.property.accountprop.AccountNumber;
import model.property.accountprop.CurrentBalance;
import model.property.accountprop.CustomerIdNumber;
import model.property.accountprop.LastTransactionDateTime;
import model.property.clientprop.DateOfBirth;
import model.property.clientprop.FirstName;
import model.property.clientprop.IdNumber;
import model.property.clientprop.LastName;

public class Configurer {

    private static Configurer INSTANCE;
    private Configurer(){}
    public static Configurer getInstance(){
        if( INSTANCE == null )
            INSTANCE = new Configurer();
        return INSTANCE;
    }

    public StringProcessor getConfiguredClientStringProcessor(){
        return new StringProcessorConfig()
                .addSubstringProcessor(new IdNumber())
                .addSubstringProcessor(new FirstName())
                .addSubstringProcessor(new LastName())
                .addSubstringProcessor(new DateOfBirth())
                .config();
    }

    public StringProcessor getConfiguredAccountStringProcessor(){
        return new StringProcessorConfig()
                .addSubstringProcessor(new AccountNumber())
                .addSubstringProcessor(new CurrentBalance())
                .addSubstringProcessor(new LastTransactionDateTime())
                .addSubstringProcessor(new CustomerIdNumber())
                .config();

    }
}
