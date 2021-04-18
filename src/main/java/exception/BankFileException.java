package exception;

import model.bankfile.BankFile;

public class BankFileException extends RuntimeException {

    public BankFileException(BankFile bankFile, String message) {
        super(message);
        this.bankFile = bankFile;
    }

    private final BankFile bankFile;

    public BankFile getBankFile() {
        return bankFile;
    }
}
