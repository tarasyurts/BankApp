package property.clientprop;

import org.javatuples.Triplet;
import property.PropertyProcessor;

public class FirstName implements PropertyProcessor {

    private final String propertyName = "First Name";
    private final int length = 20;

    @Override
    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {
        String str = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, str.strip(), length);
    }
}
