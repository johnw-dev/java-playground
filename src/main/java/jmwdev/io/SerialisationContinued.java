package jmwdev.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class SerialisationContinued {
    static Logger logger = LogManager.getLogger(SerialisationContinued.class); // REPLACE


    public static class Book implements Serializable {
        private String author;
        private String isbn;
        private final int authorAge;
        @Serial
        private static final long serialVersionUID = 1L;

        private static final ObjectStreamField[] fieldsToSerialise = {
                new ObjectStreamField("author", String.class),
                new ObjectStreamField("isbn", String.class)
        };

        public Book(String author, String isbn, int authorAge) {
            this.author = author;
            this.isbn = isbn;
            this.authorAge = authorAge;
        }

        @Override
        public String toString() {
            return author + " - " + isbn + " - " + authorAge;
        }

        @Serial
        private void writeObject(ObjectOutputStream oos) throws Exception {
            ObjectOutputStream.PutField fields = oos.putFields();
            fields.put("author", author);
            fields.put("isbn", isbn);
            oos.writeFields();
        }

        @Serial
        private void readObject(ObjectInputStream ois) throws Exception {
            ObjectInputStream.GetField fields = ois.readFields();
            author = (String)fields.get("author", null);
            isbn = (String)fields.get("isbn", null);
        }

    }

    public static void main(String[] args) {
        try(var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("book.ser")))) {
            Book b = new Book("Joe Bloggs", "111-2-333-55555-1", 40);
            logger.info("BEFORE: {}", b);
            out.writeObject(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try(var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("book.ser")))) {
            Book b2 = (Book)in.readObject();
            logger.info("AFTER: {}", b2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
