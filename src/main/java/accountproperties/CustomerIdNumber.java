package accountproperties;

import org.javatuples.Triplet;

public class CustomerIdNumber implements AccountProperty {

    private final String propertyName = "Customer ID Number";
    private final int length = 4;

    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {

        String result = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, Long.valueOf(result), length);
    }
}
