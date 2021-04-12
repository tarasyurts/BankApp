package config;

import service.PropertiesProcessor;
import model.property.PropertyProcessor;

import java.util.*;

public class PropertiesProcessorConfig {

    private final List<PropertyProcessor> propertyProcessorList = new ArrayList<>() ;

    public PropertiesProcessorConfig addProperty(PropertyProcessor propertyProcessor){
        propertyProcessorList.add(propertyProcessor);
        return this;
    }

    public PropertiesProcessor config(){
        return new PropertiesProcessor(propertyProcessorList);
    }

}
