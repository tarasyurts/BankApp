package model.property.clientprop;

import model.property.PropertyInfo;
import org.javatuples.Triplet;
import model.property.SubstringProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOfBirth extends PropertyInfo implements SubstringProcessor {

    private final String dateFormatPattern = "yyyyMMdd";

    public DateOfBirth() {
        super("DOB", 8);
    }

    @Override
    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {
        String str = line.substring(offset).substring(0, length);
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

        try {
            Date date = dateFormat.parse(str);
            return new Triplet<>(propertyName, date, length);
        } catch (ParseException e) {
            throw new RuntimeException("Cant parse: " + str);
        }
    }
}
