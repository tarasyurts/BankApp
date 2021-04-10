package config;

import property.PropertyProcessor;
import org.javatuples.Triplet;

import java.util.*;

public class PropertiesConfig {


    private String line;
    private int offset;
    private final List<PropertyProcessor> propertyProcessorList = new ArrayList<>() ;

    public PropertiesConfig setLine(String line){
        this.line = line;
        return this;
    }

    public PropertiesConfig addProperty(PropertyProcessor propertyProcessor){
        propertyProcessorList.add(propertyProcessor);
        return this;
    }

    public LinkedHashMap<String, Object> config(){

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        propertyProcessorList
                .forEach(accountProperty -> {
                    Triplet<String, Object, Integer> processedData = accountProperty.process(line, offset);
                    offset += processedData.getValue2();
                    result.put(processedData.getValue0(), processedData.getValue1());
                });
        return result;
    }

}
