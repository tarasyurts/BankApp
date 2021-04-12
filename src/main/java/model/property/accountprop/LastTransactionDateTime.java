package model.property.accountprop;

import org.javatuples.Triplet;
import model.property.PropertyProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastTransactionDateTime implements PropertyProcessor {

    private final String propertyName = "Last Transaction Date Time";
    private final int length = 14;
    private final String dateFormatPattern = "yyyyMMddHHmmss";

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
