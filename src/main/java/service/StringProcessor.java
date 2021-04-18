package service;

import model.BankTableData;
import org.javatuples.Triplet;
import model.property.SubstringProcessor;

import java.util.List;

public class StringProcessor {

    private String string;
    private int offset;
    private final List<SubstringProcessor> substringProcessors;

    public StringProcessor(List<SubstringProcessor> substringProcessors) {
        this.substringProcessors = substringProcessors;
    }

    public StringProcessor setString(String string){
        this.string = string;
        return this;
    }

    public BankTableData process(){
        offset = 0;
        BankTableData result = new BankTableData();
        substringProcessors
                .forEach(property -> {
                    Triplet<String, Object, Integer> processedData = property.process(string, offset);
                    offset += processedData.getValue2();
                    result.put(processedData.getValue0(), processedData.getValue1());
                });
        return result;
    }
}
