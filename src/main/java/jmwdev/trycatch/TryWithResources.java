package jmwdev.trycatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResources {
    static Logger log = LogManager.getLogger(TryWithResources.class);

    static String readFirstLineFromFile(String path) throws IOException {
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {
            return br.readLine();
        }
    }

    public static void main(String[] args) throws Exception {
        String line = readFirstLineFromFile("src/main/resources/testfile.txt");
        log.info(line);
        try (MySocket socket = new MySocket()) {
            socket.write(new byte[]{'c'});
        } catch (Exception e) {
            log.info("caught exception");
        }
    }

    static class MySocket implements AutoCloseable {

        boolean open;

        public MySocket() {
            open = true;
        }

        public void write(byte[] bytes) {
            log.info("written bytes: {}", bytes.length);
        }

        @Override
        public void close() throws Exception {
            open = false;
        }
    }
}
