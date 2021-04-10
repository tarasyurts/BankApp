import config.PropertiesConfig;
import config.Configurer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class BankApp {
    public static void main(String[] args) {

        PropertiesConfig clientPropertiesConfig = new PropertiesConfig();
        PropertiesConfig accountPropertiesConfig = new PropertiesConfig();

        Configurer.configClientProperties(clientPropertiesConfig);
        Configurer.configAccountProperties(accountPropertiesConfig);

        LinkedHashMap<String, Object> result =
                clientPropertiesConfig.setLine("1002Jessica             Greenwoodson            19911112").config();

        result.entrySet().forEach(System.out::println);

    }
}
