package jmwdev.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

class Password {
    static final int SALT_LENGTH = 100;
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    static Logger logger = LogManager.getLogger("Password");
    private final String salt;
    private final String phrase;

    public Password(char[] phrase, int saltLength) {
        this.salt = generateSalt(saltLength);
        this.phrase = hashPassword(phrase, this.salt).orElseThrow();
    }

    private static String generateSalt(final int length) {
        if (length < 1) {
            throw new IllegalArgumentException("bad salt length");
        }
        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    private static Optional<String> hashPassword(char[] password, String salt) {
        byte[] bytes = salt.getBytes();
        PBEKeySpec spec = new PBEKeySpec(password, bytes, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            logger.error(ex);
            return Optional.empty();
        } finally {
            spec.clearPassword();
        }
    }

    public boolean matches(char[] input) {
        String inputHash = hashPassword(input, this.salt).orElse("");
        return this.phrase.equals(inputHash);
    }
}
