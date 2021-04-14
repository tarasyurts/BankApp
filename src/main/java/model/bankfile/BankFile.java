package model.bankfile;


import exception.BankFileException;
import model.BankTableData;
import service.BankFileExceptionsService;
import service.FileIOService;
import service.StringProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public abstract class BankFile{

    private final String name;
    private final String path;
    private final CompletableFuture<String[]> data;
    private final StringProcessor processor;

    private BankFile associatedBankFile;

    public BankFile(String path, StringProcessor processor){
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


    public BankFile getAssociatedBankFile() {
        return associatedBankFile;
    }

    public void setAssociatedBankFile(BankFile associatedBankFile) {
        this.associatedBankFile = associatedBankFile;
    }

    public List<BankTableData> getDataProcessed() throws BankFileException {

        List<BankTableData> result = new ArrayList<>();

        String[] lines = new String[0];
        try {
            lines = data.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < lines.length; i++) {
            try {
                result.add(processor.setString(lines[i]).process());
            } catch (RuntimeException e) {
                BankFileExceptionsService.getInstance()
                        .addBankFileException(new BankFileException(this, "File name: " + name + " Line number: " + (i+1) + " Cause: " + e.toString() ));
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + path.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BankFile)) {
            return false;
        }

        return name.equals(((BankFile) obj).name) && path.equals(((BankFile) obj).path);
    }
}
