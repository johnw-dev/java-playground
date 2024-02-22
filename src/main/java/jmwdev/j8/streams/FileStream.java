package jmwdev.j8.streams;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileStream {

    static Logger logger = LogManager.getLogger(FileStream.class); // REPLACE

    public static void main(String[] args) throws IOException {
        logger.info("started {}", System.getProperty("user.dir"));
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/testfile.txt"))) {
            lines
                    .filter(x -> !x.isEmpty())
                    .map(x-> "line: "+x)
                    .forEach(logger::info);
        }
        logger.info("finished");
    }
}
