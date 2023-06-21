package jmwdev.login;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.security.SecureRandom;

class Password {
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    static final int SALT_LENGTH = 100;

    private String salt;
    private String password;

    public Password(char[] password, int saltLength) {
        this.salt = generateSalt(saltLength);
        this.password = hashPassword(password, this.salt).orElseThrow();
    }

    public boolean matches(char[] input) {
        String inputHash = hashPassword(input, this.salt).orElse("");
        return this.password.equals(inputHash);
    }

    private static String generateSalt(final int length) {
        if (length < 1) {
            throw new IllegalArgumentException("bad salt length");
        }
        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    private static Optional<String> hashPassword (char[] password, String salt) {
        byte[] bytes = salt.getBytes();
        PBEKeySpec spec = new PBEKeySpec(password, bytes, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();
        } finally {
            spec.clearPassword();
        }
    }
}
