package config;

import property.PropertiesProcessor;
import property.accountprop.AccountNumber;
import property.accountprop.CurrentBalance;
import property.accountprop.CustomerIdNumber;
import property.accountprop.LastTransactionDateTime;
import property.clientprop.DateOfBirth;
import property.clientprop.FirstName;
import property.clientprop.IdNumber;
import property.clientprop.LastName;

public class Configurer {

    public static PropertiesProcessor getConfiguredClientPropertiesProcessor(){
        return new PropertiesProcessorConfig()
                .addProperty(new IdNumber())
                .addProperty(new FirstName())
                .addProperty(new LastName())
                .addProperty(new DateOfBirth())
                .config();
    }

    public static PropertiesProcessor getConfiguredAccountPropertiesProcessor(){
        return new PropertiesProcessorConfig()
                .addProperty(new AccountNumber())
                .addProperty(new CurrentBalance())
                .addProperty(new LastTransactionDateTime())
                .addProperty(new CustomerIdNumber())
                .config();

    }


}
