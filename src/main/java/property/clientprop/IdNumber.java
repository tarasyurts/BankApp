package property.clientprop;

import org.javatuples.Triplet;
import property.PropertyProcessor;

public class IdNumber implements PropertyProcessor {

    private final String propertyName = "ID Number";
    private final int length = 4;

    @Override
    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {
        String result = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, Long.valueOf(result), length);
    }
}
