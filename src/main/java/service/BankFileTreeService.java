package service;

import exception.BankFileException;
import model.BankTableData;
import model.bankfile.AccountFileData;
import model.bankfile.BankFile;
import model.bankfile.CustomerFileData;

import org.javatuples.Pair;

import java.util.ArrayList;
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

//        List<Pair<BankFile, List<BankTableData>>> allProcessedFiles = new ArrayList<>();
//        for(BankFile topNode: topNodes){
//            while (topNode != null){
//                allProcessedFiles.add(new Pair<>(topNode, topNode.getFileData().getProcessed()));
//                topNode = topNode.getAssociatedBankFile();
////                processed.forEach(resultMap -> resultMap.entrySet().forEach(System.out::println));
//            }
//        }

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

//        return allProcessedFiles.stream()
//                .filter(pair -> allowedTopNodes.contains(pair.getValue0())).collect(Collectors.toList());
    }

    public void save(List<BankFile> topNodes){
        for(BankFile topNode: topNodes){
            while (topNode != null){
                topNode.getFileData().save();
                topNode = topNode.getAssociatedBankFile();
            }
        }
    }

}
