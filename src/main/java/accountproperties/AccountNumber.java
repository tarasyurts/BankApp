package accountproperties;

import org.javatuples.Triplet;

public class AccountNumber implements AccountProperty {

    private final String propertyName = "Account Number";
    private final int length = 11;

    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {

        String result = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, Long.valueOf(result), length);
    }
}
