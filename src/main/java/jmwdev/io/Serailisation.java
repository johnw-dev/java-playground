package jmwdev.io;

import java.io.*;

public class Serailisation {

    static class Image {

    }
    static class User implements Serializable {

        // We don't need the serial version uid but this is important when we change the definition of the class
        //        @Serial
        //        private static final long serialVersionUID = 1234567L;

        // without transient this throws: java.io.NotSerializableException: jmwdev.io.Serailisation$Image
        private transient final Image image;
        private final String username;

        public User(String username, Image image) {
            this.username = username;
            this.image = image;
        }

        public boolean test(Image input) {
            return image.hashCode() == input.hashCode();
        }

        public String getUsername() {
            return this.username;
        }

    }

    public static void main(String[] args) throws IOException {
        User user = new User("joe", new Image());
        try(var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("User.ser")))) {
            out.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
