package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Console.AccountView;
import com.company.Views.ConsoleInput;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;
import java.util.Comparator;


public class AccountController {
    AccountView view = new AccountView();
    public static void createAccount(String username, String password) {
        if (!usernameExists(username)) {
            Account.addToAccounts(
                    new Account(username, password)
            );
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.USERNAME_EXISTS);
        }
    }

    public static void addCardToCollection(Account account ,Card card){
        account.getCollection().getCards().add(card);
    }

    public static void removeCardFromCollection(Account account ,Card card){
        account.getCollection().getCards().remove(card);
    }

    public static void loginAccount(String username, String password) {
        ErrorType loginErrorType = null;
        if (!usernameExists(username)) {
            loginErrorType = ErrorType.USERNAME_NOTFOUND;
        } else {
            if (Account.getAccountByUsername(username).isPasswordCorrect(password)) {
                Account.login(username, password);
                ConsoleInput.setMenu(ConsoleInput.Menu.MAIN);
            } else {
                loginErrorType = ErrorType.PASSWORD_INVALID;
            }
        }
        if (loginErrorType != null) {
            ConsoleOutput.printErrorMessage(loginErrorType);
        }
    }

    public static void logout() {
        Account.getLoggedInAccount().logout();
        ConsoleInput.setMenu(ConsoleInput.Menu.ACCOUNT);
    }

    public static void showLeaderBoard() {
        ArrayList<Account> leaderBoard = Account.getAccounts();
        leaderBoard.sort((o1, o2) -> {
            if (o1.getWins() == o2.getWins()){
                return o1.getUsername().compareTo(o2.getUsername());
            } else {
                return o1.getWins() - o2.getWins();
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

}
