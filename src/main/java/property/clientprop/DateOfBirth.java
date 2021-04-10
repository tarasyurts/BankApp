package property.clientprop;

import org.javatuples.Triplet;
import property.PropertyProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOfBirth implements PropertyProcessor {

    private final String propertyName = "DOB";
    private final int length = 8;
    private final String dateFormatPattern = "yyyyMMdd";

    @Override
    public Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException {
        String str = line.substring(offset).substring(0, length);
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

        try {
            Date date = dateFormat.parse(str);
            return new Triplet<>(propertyName, date, length);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
