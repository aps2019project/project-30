package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Console.AccountView;
import com.company.Views.ConsoleInput;
import com.company.Views.ConsoleOutput;

import java.io.*;
import java.util.ArrayList;


public class AccountController {

    AccountView view = new AccountView();

    public static void createAccount(String username, String password) {
        ErrorType errorType = null;
        if (!usernameExists(username)) {
            Account newAccount = new Account(username, password);
            Account.addToAccounts(newAccount);
            saveAccount(newAccount, accountFileAddress(readNumberOfAllRegisteredAccountsFromFile()));
        } else {
            ConsoleOutput.printErrorMessage(ErrorType.USERNAME_EXISTS);
            errorType = ErrorType.USERNAME_EXISTS;
        }
        AuthenticateController.signUpError(errorType);


    }

    public static void addCardToCollection(Account account, Card card) {
        account.getCollection().getCards().add(card);
    }

    public static void removeCardFromCollection(Account account, Card card) {
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
        AuthenticateController.loginError(loginErrorType);
    }

    public static void logout() {
        Account.getLoggedInAccount().logout();
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

    private static int getAccountIndexByUsername(String username) {
        int numberOfAccounts = readNumberOfAllRegisteredAccountsFromFile();
        Account account = null;
        for (int i = 0; i < numberOfAccounts; i++) {
            account = (Account) readObjectFromFile(accountFileAddress(i));
            if (account.getUsername().equals(username))
                return i;
        }
        return -1;
    }

    private static void saveAccount(Account account, String accountsFileAddress) {
        writeObjectToFile(account, accountsFileAddress);
        incrementNumberOfRegisteredAccounts();
    }

    public static void writeObjectToFile(Object object, String fileAddress) {
        try {
            ObjectOutputStream writer = setAccountWriterToFile(fileAddress);
            writer.writeObject(object);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("writing was unsuccessful");
        }
    }

    private static ObjectOutputStream setAccountWriterToFile(String fileName) throws IOException {
        return new ObjectOutputStream(new FileOutputStream(fileName));
    }

    private static Object readObjectFromFile(String fileAddress) {
        try {
            ObjectInputStream reader = setAccountReaderFromFile(fileAddress);
            Object readObject = reader.readObject();
            reader.close();
            return readObject;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static ObjectInputStream setAccountReaderFromFile(String fileName) throws IOException {
        return new ObjectInputStream(new FileInputStream(fileName));
    }

    private static int readNumberOfAllRegisteredAccountsFromFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Account.getNumberOfAccountsFileAddress()));
            String numberOfAllRegisteredAccounts = bufferedReader.readLine();
            System.out.println(numberOfAllRegisteredAccounts);
            bufferedReader.close();
            if (numberOfAllRegisteredAccounts != null)
                return Integer.parseInt(numberOfAllRegisteredAccounts);
        } catch (IOException e) {
            System.err.println("file not found");
        }
        return 0;
    }

    private static void incrementNumberOfRegisteredAccounts() {
        try {
            int registeredAccountsNumber = readNumberOfAllRegisteredAccountsFromFile() + 1;
            PrintWriter writer = new PrintWriter(Account.getNumberOfAccountsFileAddress());
            writer.print(String.valueOf(registeredAccountsNumber));
            writer.close();
        } catch (IOException e) {
            System.err.println("incrementing registered accounts number failed");
        }

    }

    private static String accountFileAddress(int index) {
        return Account.getAccountsFolderAddress() + "Account" + index + ".txt";
    }

    private static void removeFile(String fileAddress) {
        File file = new File(fileAddress);
        file.delete();
    }

    public static void addSavedAccountsToAccounts() {
        int numberOfAccounts = readNumberOfAllRegisteredAccountsFromFile();
        for (int i = 0; i < numberOfAccounts; i++) {
            Account.addToAccounts((Account) readObjectFromFile(accountFileAddress(i)));
        }
    }
}