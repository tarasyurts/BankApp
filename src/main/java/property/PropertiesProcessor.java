package property;

import org.javatuples.Triplet;

import java.util.LinkedHashMap;
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

    public LinkedHashMap<String, Object> process(){
        offset = 0;
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        propertyProcessors
                .forEach(property -> {
                    Triplet<String, Object, Integer> processedData = property.process(line, offset);
                    offset += processedData.getValue2();
                    result.put(processedData.getValue0(), processedData.getValue1());
                });
        return result;
    }
}
