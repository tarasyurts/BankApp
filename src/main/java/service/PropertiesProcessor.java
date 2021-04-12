package service;

import model.ProcessedRow;
import org.javatuples.Triplet;
import model.property.PropertyProcessor;

import java.util.List;

public class PropertiesProcessor {

    private String line;
    private int offset;
    private final List<PropertyProcessor> propertyProcessors;

    public PropertiesProcessor(List<PropertyProcessor> propertyProcessors) {
        this.propertyProcessors = propertyProcessors;
    }

    public PropertiesProcessor setLine(String line){
        this.line = line;
        return this;
    }

    public ProcessedRow process(){
        offset = 0;
        ProcessedRow result = new ProcessedRow();
        propertyProcessors
                .forEach(property -> {
                    Triplet<String, Object, Integer> processedData = property.process(line, offset);
                    offset += processedData.getValue2();
                    result.put(processedData.getValue0(), processedData.getValue1());
                });
        return result;
    }
}
