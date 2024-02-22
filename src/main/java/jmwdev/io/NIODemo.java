package jmwdev.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;

public class NIODemo {
    static Logger logger = LogManager.getLogger(NIODemo.class); // REPLACE

    public static void main(String[] args) throws IOException {
        Path p1 = Path.of("src/main/testfile.txt");
        Path p3 = Path.of("src/main/","testfile3.txt");
        Path p2 = Paths.get("src/main/testfile2.txt"); // also supports varargs

        logger.info(p1.getFileName());
        logger.info(p1.getName(0));
        logger.info(p1.getParent());
        logger.info(p1.getRoot());
        logger.info(p1.subpath(1,2));

        var pSource = Paths.get("src/main/resources/testfile.txt");
        var pTarget1 = Paths.get("src/main/resources/nio/test/testfile.txt");
        var pTarget2 = Paths.get("src/main/resources/nio/test/testfile2.txt");
        if(Files.exists(pSource)) {
            logger.info("{} bytes", Files.size(pSource));
            logger.info("{} bytes", Files.readAttributes(pSource, BasicFileAttributes.class).size());
            logger.info(FileTime.fromMillis(Files.getLastModifiedTime(pSource).toMillis()).toInstant().atZone(ZoneId.of("Europe/London")));
            if(!Files.exists(pTarget1)) {
                Files.createDirectories(pTarget1.getParent());
                    Files.copy(pSource, pTarget1);
                    Files.move(pTarget1, pTarget2);
                    Files.delete(pTarget2);

                }
        }
        BasicFileAttributeView view = Files.getFileAttributeView(pSource, BasicFileAttributeView.class);
        var attrs = view.readAttributes();
        logger.info("Before: {}",attrs.lastModifiedTime());
        FileTime lastModified = FileTime.fromMillis(attrs.lastModifiedTime().toMillis()+1000);
        view.setTimes(lastModified, null, null);
        logger.info("After: {}",Files.readAttributes(pSource, BasicFileAttributes.class).lastModifiedTime());



        Path partial1 = Path.of("src/main/");
        Path partial2 = Path.of("testfile.txt");
        var result = partial1.resolve(partial2);
        logger.info("{} {} {}",partial1, partial2, result);
    }
}
