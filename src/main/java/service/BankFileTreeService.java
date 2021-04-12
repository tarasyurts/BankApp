package service;

import constant.Constants;
import exception.BankFileException;
import model.ProcessedRow;
import model.bankfile.AccountBankFile;
import model.bankfile.BankFile;
import model.bankfile.CustomerBankFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankFileTreeService {

    private static BankFileTreeService INSTANCE;
    private BankFileTreeService(){}
    public static BankFileTreeService getInstance(){
        if( INSTANCE == null )
            INSTANCE = new BankFileTreeService();
        return INSTANCE;
    }


    public List<BankFile> formAssociations(List<BankFile> allBankFiles){

        List<BankFile> accountBankFiles =
                allBankFiles.stream()
                        .filter(bankFile -> bankFile instanceof AccountBankFile).collect(Collectors.toList());

        return allBankFiles.stream().filter( bankFile -> bankFile instanceof CustomerBankFile)
                .map( bankFile -> {
                            Date date = BankFileService.getInstance().getDateFromFileName(bankFile.getName());
                            bankFile.setAssociatedBankFile(accountBankFiles.stream()
                                    .filter(accountBankFile ->
                                            BankFileService.getInstance().getDateFromFileName(bankFile.getName()).equals(date)
                                    ).findFirst().orElse(null));
                            return bankFile;
                        }).collect(Collectors.toList());
    }

    public List<BankFile> tryProcess(List<BankFile> topNodes){

        for(BankFile topNode: topNodes){
            try{
                while (topNode != null){
                    List<ProcessedRow> processed = topNode.getProcessed();
                    topNode = topNode.getAssociatedBankFile();
                }
            }catch (BankFileException ex){
                try {
                    FileIOService.getInstance().writeLine(Constants.ERRORS_FILEPATH, ex.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

//            processed.forEach(resultMap -> resultMap.entrySet().forEach(System.out::println));

}
