package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DecoratorStream extends OutputStream {
    static Logger logger = LogManager.getLogger(DecoratorStream.class);

    private OutputStream stream;

    private String prefix;


    public DecoratorStream(OutputStream stream, String prefix) {
        super();
        this.stream = stream;
        this.prefix = prefix;
    }

    public static void main(String[] args) throws IOException {
        byte[] message = new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c, 0x20, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x21};
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DecoratorStream decoratorStream = new DecoratorStream(baos, "First line: ");
            decoratorStream.write(message);
            decoratorStream.write(message);
            decoratorStream.write(message);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), StandardCharsets.UTF_8))) {
                String result = reader.readLine();
                logger.info(result);  //should print "First line: Hello, world!"
            }

        }

    }

    @Override

    public void write(int b) throws IOException {
        byte[] result = new byte[4];
        result[0] = (byte) (b >> 24);
        result[1] = (byte) (b >> 16);
        result[2] = (byte) (b >> 8);
        result[3] = (byte) (b);
        write(result, 0, 4);

    }

    @Override

    public void write(byte[] b, int off, int len) throws IOException {
        // convert prefix to bytes.
        if (prefix != null && !prefix.isEmpty()) {
            byte[] prefixBytes = prefix.getBytes(StandardCharsets.UTF_8);
            stream.write(prefixBytes);
            prefix = null;
        }
        stream.write(b);
    }

    @Override

    public void write(byte[] b) throws IOException {

        write(b, 0, b.length);

    }

}