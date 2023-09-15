package jmwdev.testdome;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AccountTest {

    @Test
    void accountCannotHaveNegativeOverdraftLimit() {
        Account account = new Account(-20);
        double epsilon = 1e-6;
        assertEquals(0d, account.getOverdraftLimit(), epsilon);
    }

    @Test
    void depositCannotHaveNegativeAmount() {
        Account account = new Account(0);
        assertFalse(account.deposit(-1.0));
    }

    @Test
    void withdrawCannotHaveNegativeAmount() {
        Account account = new Account(0);
        assertFalse(account.deposit(-1.0));
    }


    @Test
    void depositSetsBalanceCorrectlyAndReturnCorrectResult() {
        Account account = new Account(0);
        assertTrue(account.deposit(1.0));
        assertEquals(1.0, account.getBalance());

    }


    @Test
    void withdrawSetsBalanceCorrectlyAndReturnCorrectResult() {
        Account account = new Account(0);
        assertTrue(account.deposit(3.0));
        assertTrue(account.withdraw(1.0));
        assertEquals(2.0, account.getBalance());

    }
}