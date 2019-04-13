package com.company.Controllers;

import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.ConsoleOutput;


public class AccountController {
    public static void createAccount(String username, String password) {
        if (!usernameExists(username)) {
            Account.addToAccounts(
                    new Account(username, password)
            );
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.USERNAME_EXISTS);
        }
    }

    public static void loginAccount(String username, String password) {
        if (!usernameExists(username)) {
            ConsoleOutput.printErrorMessage(ErrorType.USERNAME_NOTFOUND);
        } else {
            if (Account.getAccountByUsername(username).isPasswordCorrect(password)) {
                //todo : login
            } else {
                ConsoleOutput.printErrorMessage(ErrorType.PASSWORD_INVALID);
            }
        }
    }

    private static boolean usernameExists(String username) {
        for (Account account : Account.getAccounts()) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
