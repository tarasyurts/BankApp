package file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO {


//    public String[] read(String filepath) throws IOException, URISyntaxException {
//
//        Path path = Paths.get(getClass().getClassLoader()
//                .getResource(filepath).toURI());
//        Stream<String> lines = Files.lines(path);
//        return lines.toArray(String[]::new);
//
//
//    }


    public static String[] read(String filePath) throws IOException, NullPointerException {
        String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        return content.lines().toArray(String[]::new);
    }

    public static void write(String filePath, String data, StandardOpenOption standardOpenOption) throws  IOException{
        Files.writeString(Paths.get(filePath), data, standardOpenOption);
    }
}
