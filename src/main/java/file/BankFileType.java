package file;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BankFileType {
    ACCOUNT_INFO,
    CLIENT_INFO;

    public String getAbbreviation(){
        return Stream.of(name().replaceAll("_"," ").split(" "))
                .map(s -> String.valueOf(s.charAt(0)))
                .collect(Collectors.joining());
    }

    public static Stream<BankFileType> stream(){
        return Stream.of(BankFileType.values());
    }
}