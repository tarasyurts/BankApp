package model.bankfile;


import exception.BankFileException;
import model.ProcessedRow;
import service.BankFileExceptionsService;
import service.PropertiesProcessor;
import service.FileIOService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public abstract class BankFile{

    private final String name;
    private final String path;
    private final CompletableFuture<String[]> data;
    private final PropertiesProcessor processor;

    private BankFile associatedBankFile;

    public BankFile(String path, PropertiesProcessor processor){
        this.name = new File(path).getName();
        this.path = path;
        this.data = CompletableFuture.supplyAsync(()-> {
            try {
                return FileIOService.getInstance().read(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new String[0];
        });
        this.processor = processor;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String[] getData() {

        String[] someData = new String[0];
        try {
            someData = data.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return someData;
    }

    public BankFile getAssociatedBankFile() {
        return associatedBankFile;
    }

    public void setAssociatedBankFile(BankFile associatedBankFile) {
        this.associatedBankFile = associatedBankFile;
    }

    public abstract String getAbbreviation();

    public List<ProcessedRow> getProcessed() throws BankFileException {

        List<ProcessedRow> result = new ArrayList<>();

        String[] lines = new String[0];
        try {
            lines = data.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < lines.length; i++) {
            try {
                result.add(processor.setLine(lines[i]).process());
            } catch (RuntimeException e) {
                BankFileExceptionsService.getInstance()
                        .add(new BankFileException(this, "File name: " + name + " Line number: " + (i+1) + " Cause: " + e.toString() ));
            }
        }
        return result;
    }

}
