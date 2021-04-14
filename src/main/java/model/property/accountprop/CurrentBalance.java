package model.property.accountprop;

import model.property.PropertyInfo;
import org.javatuples.Triplet;
import model.property.SubstringProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CurrentBalance extends PropertyInfo implements SubstringProcessor {

    private final String pattern = "[^0]\\d*";

    public CurrentBalance() {
        super("Current Balance", 10);
    }

    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {

        String str = line.substring(offset).substring(0, length);

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while (m.find()) {
            str = m.group(0);
        }

        str = str.substring(0, str.length() - 2) + "." + str.substring(str.length() - 2);
        return new Triplet<>(propertyName, Double.valueOf(str), length);
    }
}
