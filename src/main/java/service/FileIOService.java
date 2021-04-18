package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class FileIOService {

    private static FileIOService INSTANCE;
    private FileIOService(){}
    public static FileIOService getInstance(){
        if( INSTANCE == null )
            INSTANCE = new FileIOService();
        return INSTANCE;
    }

    public String[] read(String filePath) throws IOException, NullPointerException {
        String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        return content.lines().toArray(String[]::new);
    }
    
    public void writeLine(String filePath, String line) throws IOException {
        String s = System.lineSeparator() + line;
        Files.write(Paths.get(filePath), s.getBytes(), StandardOpenOption.APPEND);
    }
}
