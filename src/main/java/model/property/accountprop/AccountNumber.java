package model.property.accountprop;

import model.property.PropertyInfo;
import org.javatuples.Triplet;
import model.property.SubstringProcessor;

public class AccountNumber extends PropertyInfo implements SubstringProcessor {

    public AccountNumber() {
        super("Account Number", 11);
    }

    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {

        String result = line.substring(offset).substring(0, length);
        return new Triplet<>(propertyName, Long.valueOf(result), length);
    }
}
