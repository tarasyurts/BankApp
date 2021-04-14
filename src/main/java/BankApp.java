import exception.BankFileException;
import model.BankTableData;
import model.bankfile.BankFile;
import org.javatuples.Pair;
import service.BankFileExceptionsService;
import service.BankFileService;
import service.BankFileTreeService;

import java.util.List;

public class BankApp {
    public static void main(String[] args) {

        List<BankFile> bankFiles = BankFileService.getInstance().readNewBankFiles();
        List<BankFile> topNodes = BankFileTreeService.getInstance().formAssociations(bankFiles);

        List<Pair<BankFile, List<BankTableData>>> pairs = BankFileTreeService.getInstance().tryProcess(topNodes);
        BankFileExceptionsService.getInstance().getBankFileExceptions().stream()
                .map(BankFileException::getMessage).forEach(System.out::println);

    }
}
