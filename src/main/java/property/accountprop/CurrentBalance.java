package property.accountprop;

import org.javatuples.Triplet;
import property.PropertyProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CurrentBalance implements PropertyProcessor {

    private final String propertyName = "Current Balance";
    private final int length = 10;
    private final String pattern = "[^0]\\d*";

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
