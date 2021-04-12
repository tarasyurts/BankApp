package config;

import service.PropertiesProcessor;
import model.property.accountprop.AccountNumber;
import model.property.accountprop.CurrentBalance;
import model.property.accountprop.CustomerIdNumber;
import model.property.accountprop.LastTransactionDateTime;
import model.property.clientprop.DateOfBirth;
import model.property.clientprop.FirstName;
import model.property.clientprop.IdNumber;
import model.property.clientprop.LastName;

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
