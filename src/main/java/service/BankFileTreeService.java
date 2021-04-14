package service;

import exception.BankFileException;
import model.BankTableData;
import model.bankfile.AccountBankFile;
import model.bankfile.BankFile;
import model.bankfile.CustomerBankFile;

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

    public List<Pair<BankFile, List<BankTableData>>> tryProcess(List<BankFile> topNodes){

        List<Pair<BankFile, List<BankTableData>>> allProcessedFiles = new ArrayList<>();
        for(BankFile topNode: topNodes){
            while (topNode != null){
                allProcessedFiles.add(new Pair<>(topNode, topNode.getDataProcessed()));
                topNode = topNode.getAssociatedBankFile();
//                processed.forEach(resultMap -> resultMap.entrySet().forEach(System.out::println));
            }
        }

        List<BankFile> allowedTopNodes = topNodes.stream()
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

        return allProcessedFiles.stream()
                .filter(pair -> allowedTopNodes.contains(pair.getValue0())).collect(Collectors.toList());
    }


}
