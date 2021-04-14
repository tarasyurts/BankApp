package model.property.clientprop;

import model.property.PropertyInfo;
import org.javatuples.Triplet;
import model.property.SubstringProcessor;

public class IdNumber extends PropertyInfo implements SubstringProcessor {

    public IdNumber() {
        super("ID Number", 4);
    }

    @Override
    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {
        String result = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, Long.valueOf(result), length);
    }
}
