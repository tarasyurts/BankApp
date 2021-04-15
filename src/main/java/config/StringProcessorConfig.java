package config;

import service.StringProcessor;
import model.property.SubstringProcessor;

import java.util.*;

public class StringProcessorConfig {

    private final List<SubstringProcessor> substringProcessors = new ArrayList<>() ;

    public StringProcessorConfig addSubstringProcessor(SubstringProcessor substringProcessor){
        substringProcessors.add(substringProcessor);
        return this;
    }

    public StringProcessor config(){

        // TODO: Add exception if list is empty
        return new StringProcessor(substringProcessors);
    }

}
