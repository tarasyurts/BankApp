import config.Configurer;
import file.BankFile;
import file.BankFileManager;
import property.PropertiesProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class BankApp {
    public static void main(String[] args) {

        List<BankFile> clientBankFiles;
        List<BankFile> toSaveClientBankFiles = new ArrayList<>();

        try {
            clientBankFiles = BankFileManager.readBankFiles();

            for (BankFile clientBankFile : clientBankFiles) {

                BankFile tempBankFile = clientBankFile;
                do {
                    List<LinkedHashMap<String, Object>> processed = tempBankFile.getProcessed();

                    processed.forEach(resultMap -> resultMap.entrySet().forEach(System.out::println));

                    tempBankFile = tempBankFile.getAssociatedBankFile();
                } while ( tempBankFile != null);
                toSaveClientBankFiles.add(clientBankFile);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        toSaveClientBankFiles.forEach(clientBankFile -> {
            try {
                BankFile tempBankFile = clientBankFile;
                do {
                    BankFileManager.removeProcessed(tempBankFile);
                    tempBankFile = tempBankFile.getAssociatedBankFile();
                } while ( tempBankFile != null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
