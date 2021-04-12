package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import constant.Constants;
import org.javatuples.Pair;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BankFileManager {

    public static List<BankFile> readBankFiles() throws IOException{

        return findPathsByPattern(Constants.NEW_DATA_FOLDER, Constants.CUSTOMER_INFO_FILE_NAME_PATTERN).stream()
                .map(path -> {
                    BankFile bankFile = new ClientBankFile(path.toAbsolutePath().toString());
                    try {
                        BankFile accountBankFile =
                                new AccountBankFile(getPathByDateAbbr(getDateFromFileName(bankFile.getName()), "AI").toString());
                        bankFile.setAssociatedBankFile(accountBankFile);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return bankFile;
                }).collect(Collectors.toList());
    }

    public static void removeProcessed(BankFile bankFile) throws IOException {
        Files.move(Paths.get(bankFile.getPath()),
                Paths.get(Constants.PROCESSED_DATA_FOLDER + "/" + bankFile.getName()));
    }

    private static List<Path> findPathsByPattern(String folder, String pattern) throws IOException{
        return Files.find(Paths.get(folder),
                Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(pattern)
        ).collect(Collectors.toList());
    }

    private static Date getDateFromFileName(String fileName) throws ParseException{
        String matchedString = "";
        Pattern p = Pattern.compile(Constants.FILE_NAME_DATE_PATTERN);
        Matcher m = p.matcher(fileName);
        while (m.find()) {
            matchedString = m.group(0);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FILE_NAME_DATE_FORMAT);
        return dateFormat.parse(matchedString);
    }

    private static Path getPathByDateAbbr(Date date, String abbreviation){
        DateFormat dateFormat = new SimpleDateFormat(Constants.FILE_NAME_DATE_FORMAT);
        String strDate = dateFormat.format(date);
        return Paths.get(Constants.NEW_DATA_FOLDER+ "/" + abbreviation + strDate + ".txt");
    }

//    private static BankFile findBankFileByDate(String abbreviation, Date date){
//
//        DateFormat dateFormat = new SimpleDateFormat(Constants.FILE_NAME_DATE_FORMAT);
//        String strDate = dateFormat.format(date);
//        return new BankFile(Constants.NEW_DATA_FOLDER+ "/" + bankFileType.getAbbreviation() + strDate + ".txt",
//                bankFileType);
//    }
}
