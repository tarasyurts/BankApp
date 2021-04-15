package service;

import exception.BankFileException;
import model.bankfile.AccountFileData;
import model.bankfile.BankFile;
import model.bankfile.CustomerFileData;

import java.util.Date;
import java.util.List;
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
                        .filter(bankFile -> bankFile.getFileData() instanceof AccountFileData).collect(Collectors.toList());

        return allBankFiles.stream().filter( bankFile -> bankFile.getFileData() instanceof CustomerFileData)
                .map( bankFile -> {
                            Date date = BankFileService.getInstance().getDateFromFileName(bankFile.getName());
                            bankFile.setAssociatedBankFile(accountBankFiles.stream()
                                    .filter(accountBankFile ->
                                            BankFileService.getInstance().getDateFromFileName(accountBankFile.getName()).equals(date)
                                    ).findFirst().orElse(null));
                            return bankFile;
                        }).collect(Collectors.toList());
    }

    public List<BankFile> getSuitable(List<BankFile> topNodes){
        return topNodes.stream()
                .filter(bankFile -> {
                    while (bankFile != null) {
                        BankFile finalBankFile = bankFile;
                        if (BankFileExceptionsService.getInstance()
                                .getBankFileExceptions().stream()
                                .map(BankFileException::getBankFile)
                                .anyMatch(bankFile1 -> bankFile1 == finalBankFile)) return false;
                        bankFile = bankFile.getAssociatedBankFile();
                    }
                    return true;
                }).collect(Collectors.toList());
    }

    public void save(List<BankFile> topNodes){
        for(BankFile topNode: topNodes){
            while (topNode != null){
                topNode.getFileData().save();
                topNode = topNode.getAssociatedBankFile();
            }
        }
    }

    public void moveProcessed(List<BankFile> topNodes){
        for(BankFile topNode: topNodes){
            while (topNode != null){
                BankFileService.getInstance().moveProcessed(topNode);
                topNode = topNode.getAssociatedBankFile();
            }
        }
    }

}
