package jmwdev.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SearchDirectory {
    static Path startPath = Path.of("src");
    static Logger logger = LogManager.getLogger(SearchDirectory.class); // REPLACE


    public static void main(String[] args) throws IOException {
        try(var streamPaths = Files.find(startPath, 10, (path, attr) -> attr.isRegularFile() && path.toString().endsWith(".java"))) {
            streamPaths.forEach(logger::info);
        }
    }
}
