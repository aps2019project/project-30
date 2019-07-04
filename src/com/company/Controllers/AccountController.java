package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Console.AccountView;
import com.company.Views.ConsoleInput;
import com.company.Views.ConsoleOutput;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AccountController {
    public static final int TOKEN_LENGTH = 20;

    public static void createAccount(String username, String password) {
        ErrorType errorType = null;
        if (!usernameExists(username)) {
            Account newAccount = new Account(username, password);
            Account.addToAccounts(newAccount);
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.USERNAME_EXISTS);
            errorType = ErrorType.USERNAME_EXISTS;
        }
        AuthenticateController.signUpError(errorType);

    }

    public static void signup(String username, String password) throws Account.SignupException {
        if (username.isEmpty() || password.isEmpty()) {
            throw new Account.SignupException("Username/Password Is Empty");
        } else if (usernameExists(username)) {
            throw new Account.SignupException("Username Is Already Exists");
        } else {
            Account.addToAccounts(
                    new Account(username, password));
        }
    }

    public static void addCardToCollection(Account account, Card card) {
        account.getCollection().getCards().add(card);
    }

    public static void removeCardFromCollection(Account account, Card card) {
        account.getCollection().getCards().remove(card);
    }

    public static String login(String username, String password) throws LoginException {
        if (usernameExists(username)) {
            Account account = Account.getAccountByUsername(username);
            if (account.getPassword().equals(password)) {
                String token = generateToken();
                account.setToken(token);
                return token;
            } else {
                throw new LoginException("Password is not valid");
            }
        } else {
            throw new LoginException("Username not found");
        }
    }

    public static void logout() {
        Account.logout();
        ConsoleInput.setMenu(ConsoleInput.Menu.ACCOUNT);
    }

    public static void showLeaderBoard() {
        ArrayList<Account> leaderBoard = Account.getAccounts();
        leaderBoard.sort((o1, o2) -> {
            if (o1.getWins() == o2.getWins()) {
                return o1.getUsername().compareTo(o2.getUsername());
            } else {
                return o2.getWins() - o1.getWins();
            }
        });
        AccountView.printLeaderBoard(leaderBoard);
    }

    private static boolean usernameExists(String username) {
        for (Account account : Account.getAccounts()) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void saveAccounts() {
        JsonController.removeFile(Account.getSavedAccountsFilePath());
        JsonController.writeAllAccountsOnFile();
    }

    public static void saveLoggedInAccount() {
        JsonController.removeFile(Account.getSavedAccountsFilePath());
        JsonController.writeLoggedInAccountOnFile();
    }

    public static void LoadSavedAccountsAndAddToAccounts() {
        List<Account> accounts = JsonController.getAccounts();
        if (accounts != null)
            Account.addToAccounts(accounts);
    }

    public static void loadLoggedInAccount() {
        Account account = JsonController.getLoggedInAccounts();
        if (account != null) {
            MainMenuController.changeIsRememberMe();
            Account.login(account);
        }
    }

    public static String generateToken() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}