package model.property.clientprop;

import org.javatuples.Triplet;
import model.property.PropertyProcessor;

public class LastName implements PropertyProcessor {

    private final String propertyName = "Last Name";
    private final int length = 24;

    @Override
    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {
        String str = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, str.strip(), length);
    }
}
