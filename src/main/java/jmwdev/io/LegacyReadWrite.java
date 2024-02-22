package jmwdev.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class LegacyReadWrite {
    static Logger logger = LogManager.getLogger(LegacyReadWrite.class); // REPLACE

    public static void main(String[] args) {
        try (var rdr = new BufferedReader(new FileReader("src/main/resources/testfile.txt"))) {
            var wrt = new BufferedWriter(new FileWriter("src/main/resources/testfile2.txt"));
            rdr.lines().forEach((l) -> {
                try {
                    wrt.write(l+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        wrt.close();
                    } catch (IOException e) {
                        // swallow
                    }
                }
            });
            wrt.close();
        } catch ( IOException e) {
            logger.error(e);
        }
    }
}
