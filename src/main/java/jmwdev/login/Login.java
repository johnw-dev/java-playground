package jmwdev.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;


public class Login {
    private static final Map<String, Password> credentialsCache = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    static Logger logger = LogManager.getLogger("Login");

    public static void initialisePassword() {
        String myInitialPassword = "password";
        credentialsCache.put("admin", new Password(myInitialPassword.toCharArray(), Password.SALT_LENGTH));
    }

    public static String getUser() {
        logger.info("username:");
        return scanner.nextLine();
    }

    public static char[] getPass() {
        logger.info("password:");
        // not exactly securely getting pass from user but for the notional problem in commandline
        return scanner.nextLine().toCharArray();
    }

    static Optional<String> loginChallenge() {
        for (int i = 0; i < 3; i++) {
            String user = getUser();
            char[] pass = getPass();
            if (credentialsCache.get(user).matches(pass)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        initialisePassword();
        try {
            String user = loginChallenge().orElseThrow(AccessDeniedException::new);
            logger.info("welcome {}", user);
        } catch (AccessDeniedException e) {
            logger.info("Access denied");
        }
    }
}
