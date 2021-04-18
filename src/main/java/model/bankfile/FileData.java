package model.bankfile;

import exception.BankFileException;
import model.BankTableData;
import model.DataBaseSever;
import service.BankFileExceptionsService;
import service.FileIOService;
import service.StringProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileData implements DataBaseSever {
    private String[] rowData;
    private BankFile bankFile;
    protected List<BankTableData> processedData = new ArrayList<>();
    private StringProcessor processor;

    public FileData(BankFile bankFile, StringProcessor processor) {
        this.bankFile = bankFile;
        this.processor = processor;

        try {
            this.rowData = FileIOService.getInstance().read(bankFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        getProcessed();
    }

    public void getProcessed(){

        for(int i = 0; i < rowData.length; i++) {
            try {
                processedData.add(processor.setString(rowData[i]).process());
            } catch (RuntimeException e) {
                BankFileExceptionsService.getInstance()
                        .addBankFileException(new BankFileException(
                                bankFile, "File name: " + bankFile.getName() + " Line number: " + (i+1) + " Cause: " + e.toString() )
                        );
            }
        }
    }
}
