import model.bankfile.BankFile;
import service.BankFileService;
import service.BankFileTreeService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BankApp {
    public static void main(String[] args) {

        List<BankFile> toSaveClientBankFiles = new ArrayList<>();

        List<BankFile> bankFiles = BankFileService.getInstance().readNewBankFiles();
        List<BankFile> topNodes = BankFileTreeService.getInstance().formAssociations(bankFiles);

        BankFileTreeService.getInstance().tryProcess(topNodes);

//        for (BankFile clientBankFile : bankFiles) {
//            BankFile tempBankFile = clientBankFile;
//            do {
//                List<LinkedHashMap<String, Object>> processed = tempBankFile.getProcessed();
//                processed.forEach(resultMap -> resultMap.entrySet().forEach(System.out::println));
//                tempBankFile = tempBankFile.getAssociatedBankFile();
//            } while ( tempBankFile != null);
//            toSaveClientBankFiles.add(clientBankFile);
//        }
//
//
//
//        toSaveClientBankFiles.forEach(clientBankFile -> {
//            BankFile tempBankFile = clientBankFile;
//            do {
//                BankFileService.getInstance().moveProcessed(tempBankFile);
//                tempBankFile = tempBankFile.getAssociatedBankFile();
//            } while ( tempBankFile != null);
//        });
    }
}
