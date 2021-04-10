package config;

import accountproperties.AccountNumber;
import accountproperties.CurrentBalance;
import accountproperties.CustomerIdNumber;
import accountproperties.LastTransactionDateTime;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PropertiesConfigurer {
    public void config(){
        AccountPropertiesConfig accountPropertiesConfig = new AccountPropertiesConfig();

        LinkedHashMap<String, Object> result = accountPropertiesConfig.setLine("340190091230002301788201711162009351001")
                .addProperty(new AccountNumber())
                .addProperty(new CurrentBalance())
                .addProperty(new LastTransactionDateTime())
                .addProperty(new CustomerIdNumber())
                .config();

        result.entrySet().forEach(System.out::println);
    }
}
