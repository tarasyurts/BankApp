package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import constant.Constants;
import model.bankfile.AccountFileData;
import model.bankfile.BankFile;
import model.bankfile.CustomerFileData;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BankFileService {

    private static BankFileService INSTANCE;
    private BankFileService(){}
    public static BankFileService getInstance(){
        if( INSTANCE == null )
            INSTANCE = new BankFileService();
        return INSTANCE;
    }

    public List<BankFile> readNewBankFiles(){

        List<BankFile> bankFiles = new ArrayList<>();

        bankFiles.addAll(findPathsByPattern(Constants.NEW_DATA_FOLDER, Constants.CUSTOMER_INFO_FILE_NAME_PATTERN).stream()
                .map(path -> {
                     BankFile b = new BankFile(path.toAbsolutePath().toString());
                     b.setFileData(new CustomerFileData(b));
                     return b;
                })
                .collect(Collectors.toList()));

        bankFiles.addAll(findPathsByPattern(Constants.NEW_DATA_FOLDER, Constants.ACCOUNT_INFO_FILE_NAME_PATTERN).stream()
                .map(path -> {
                    BankFile b = new BankFile(path.toAbsolutePath().toString());
                    b.setFileData(new AccountFileData(b));
                    return b;
                })
                .collect(Collectors.toList()));

        return bankFiles;
    }

    public void moveProcessed(BankFile bankFile){
        try {
            Files.move(Paths.get(bankFile.getPath()),
                    Paths.get(Constants.PROCESSED_DATA_FOLDER + "/" + bankFile.getName()));
        } catch (IOException e) {
            System.err.println("Cant move " + bankFile.getName() + " to new directory");
        }
    }

    private List<Path> findPathsByPattern(String folder, String pattern){

        List<Path> pathsFound = Collections.emptyList();
        try {
            pathsFound = Files.find(Paths.get(folder),
                    Integer.MAX_VALUE,
                    (path, basicFileAttributes) -> path.toFile().getName().matches(pattern)
            ).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Cant find paths by pattern. Check folder accessibility");
        }
        return pathsFound;
    }

    public Date getDateFromFileName(String fileName){
        String matchedString = "";
        Pattern p = Pattern.compile(Constants.FILE_NAME_DATE_PATTERN);
        Matcher m = p.matcher(fileName);
        while (m.find()) {
            matchedString = m.group(0);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FILE_NAME_DATE_FORMAT);
        try {
            return dateFormat.parse(matchedString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private Path getPathByDateAbbr(Date date, String abbreviation){
        DateFormat dateFormat = new SimpleDateFormat(Constants.FILE_NAME_DATE_FORMAT);
        String strDate = dateFormat.format(date);
        return Paths.get(Constants.NEW_DATA_FOLDER+ "/" + abbreviation + strDate + ".txt");
    }
}
