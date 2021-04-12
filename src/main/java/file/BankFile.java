package file;


import property.PropertiesProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public abstract class BankFile{

    private final String name;
    private final String path;
    private final CompletableFuture<String[]> data;
//    private final BankFileType bankFileType;
    private final PropertiesProcessor processor;

    private BankFile associatedBankFile;

    public BankFile(String path, PropertiesProcessor processor){
        this.name = new File(path).getName();
        this.path = path;
        this.data = CompletableFuture.supplyAsync(()-> {
            try {
                return FileIO.read(path);
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

//    public BankFileType getBankFileType() {
//        return bankFileType;
//    }

    public BankFile getAssociatedBankFile() {
        return associatedBankFile;
    }

    public void setAssociatedBankFile(BankFile associatedBankFile) {
        this.associatedBankFile = associatedBankFile;
    }

    public abstract String getAbbreviation();

    public List<LinkedHashMap<String, Object>> getProcessed() {

        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        try {
            for(String line: data.get())
                result.add(processor.setLine(line).process());
            return result;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String toString() {
        return "BankFile{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", data=" + Arrays.toString(getData()) +
                '}';
    }
}
