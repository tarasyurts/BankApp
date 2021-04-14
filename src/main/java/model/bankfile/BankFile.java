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

public class BankFile{

    private String name;
    private String path;
    private FileData fileData;
//    private final CompletableFuture<String[]> data;
//    private final StringProcessor processor;

    private BankFile associatedBankFile;

    public BankFile(String path){
        this.name = new File(path).getName();
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public FileData getFileData() {
        return fileData;
    }

    public void setFileData(FileData fileData) {
        this.fileData = fileData;
    }

    public BankFile getAssociatedBankFile() {
        return associatedBankFile;
    }

    public void setAssociatedBankFile(BankFile associatedBankFile) {
        this.associatedBankFile = associatedBankFile;
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
