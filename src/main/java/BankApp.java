import constant.Constants;
import exception.BankFileException;
import model.BankTableData;
import model.bankfile.AccountFileData;
import model.bankfile.BankFile;
import org.javatuples.Pair;
import repository.AccountRepository;
import repository.CustomerRepository;
import service.BankFileExceptionsService;
import service.BankFileService;
import service.BankFileTreeService;
import service.FileIOService;

import java.io.IOException;
import java.util.List;

public class BankApp {
    public static void main(String[] args) {

        List<BankFile> bankFiles = BankFileService.getInstance().readNewBankFiles();
        List<BankFile> topNodes = BankFileTreeService.getInstance().formAssociations(bankFiles);
        List<BankFile> suitableTopNodes = BankFileTreeService.getInstance().getSuitable(topNodes);

        BankFileTreeService.getInstance().save(suitableTopNodes);
        BankFileTreeService.getInstance().moveProcessed(suitableTopNodes);


        BankFileExceptionsService.getInstance().getBankFileExceptions().
                forEach(ex -> {
                    try {
                        FileIOService.getInstance().writeLine(Constants.ERRORS_FILEPATH, ex.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }
}
