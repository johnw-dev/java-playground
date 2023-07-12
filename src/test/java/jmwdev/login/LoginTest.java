package jmwdev.login;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


@Disabled
class LoginTest {

    @Test
    void loginShouldFailIfUserUnknown() {
        assertThrows(IOException.class, Login::loginChallenge);
        fail();
    }

    @Test
    void loginShouldPromptRetryIfPasswordIncorrect() {
        fail();
    }

    @Test
    void loginShouldFailIfPasswordEnteredIncorectly3Times() {
        fail();
    }

    @Test
    void loginShouldPassIfPasswordEnteredCorrectly() {
        fail();
    }
}
