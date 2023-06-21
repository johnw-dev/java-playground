package jmwdev.login;

import java.io.IOException;
import java.util.*;


public class Login {
    private static Map<String, Password> credentialsCache = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void initialisePassword() {
        String myInitialPassword = "password";
        credentialsCache.put("admin", new Password(myInitialPassword.toCharArray(), Password.SALT_LENGTH));
    }

    public static String getUser() {
        System.out.println("username:");
        return scanner.nextLine();
    }

    public static char[] getPass() {
        System.out.println("password:");
        // not exactly securely getting pass from user but for the notional problem in commandline
        return scanner.nextLine().toCharArray();
    }

    static Optional<String> loginChallenge() throws IOException {
        for(int i=0; i<3;i++) {
            String user = getUser();
            char[] pass = getPass();
            if(credentialsCache.get(user).matches(pass)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        initialisePassword();
        try {
            String user = loginChallenge().orElseThrow(AccessDeniedException::new);
            System.out.println("welcome "+user);
        }
        catch(AccessDeniedException | IOException e) {
            System.out.println("Access denied");
        }
    }
}
