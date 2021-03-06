package model.property.clientprop;

import model.property.PropertyInfo;
import org.javatuples.Triplet;
import model.property.SubstringProcessor;

public class FirstName extends PropertyInfo implements SubstringProcessor {

    public FirstName() {
        super("First Name", 20);
    }

    @Override
    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {
        String str = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, str.strip(), length);
    }
}
