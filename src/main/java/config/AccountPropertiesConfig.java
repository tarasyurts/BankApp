package config;

import accountproperties.AccountProperty;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;
import java.util.stream.Collectors;

public class AccountPropertiesConfig {


    private String line;
    private int offset;
    private final List<AccountProperty> accountPropertyList = new ArrayList<>() ;

    public AccountPropertiesConfig() {
    }

    public AccountPropertiesConfig setLine(String line){
        this.line = line;
        return this;
    }

    public AccountPropertiesConfig addProperty(AccountProperty accountProperty){
        accountPropertyList.add(accountProperty);
        return this;
    }

    public LinkedHashMap<String, Object> config(){

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        accountPropertyList
                .forEach(accountProperty -> {
                    Triplet<String, Object, Integer> processedData = accountProperty.process(line, offset);
                    offset += processedData.getValue2();
                    result.put(processedData.getValue0(), processedData.getValue1());
                });
        return result;
    }

}
