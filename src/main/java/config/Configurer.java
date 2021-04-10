package config;

import property.accountprop.AccountNumber;
import property.accountprop.CurrentBalance;
import property.accountprop.CustomerIdNumber;
import property.accountprop.LastTransactionDateTime;
import property.clientprop.DateOfBirth;
import property.clientprop.FirstName;
import property.clientprop.IdNumber;
import property.clientprop.LastName;

public class Configurer {

    public static void configClientProperties(PropertiesConfig clientPropsConfig){
        clientPropsConfig
                .addProperty(new IdNumber())
                .addProperty(new FirstName())
                .addProperty(new LastName())
                .addProperty(new DateOfBirth());
    }

    public static void configAccountProperties(PropertiesConfig accountPropsConfig){
        accountPropsConfig
//                .setLine("340190091230002301788201711162009351001")
                .addProperty(new AccountNumber())
                .addProperty(new CurrentBalance())
                .addProperty(new LastTransactionDateTime())
                .addProperty(new CustomerIdNumber());
//                .config();

    }


}
