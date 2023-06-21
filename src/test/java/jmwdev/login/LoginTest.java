package jmwdev.login;

import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.io.IOException;
import java.util.Optional;

import static jmwdev.login.Login.loginChallenge;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


public class LoginTest {

    @Test
    public void loginShouldFailIfUserUnknown() {
        assertThrows(IOException.class, () -> loginChallenge());
        fail();
    }

    @Test
    public void loginShouldPromptRetryIfPasswordIncorrect() {
        fail();
    }
    @Test
    public void loginShouldFailIfPasswordEnteredIncorectly3Times() {
        fail();
    }

    @Test
    public void loginShouldPassIfPasswordEnteredCorrectly() {
        fail();
    }
}
