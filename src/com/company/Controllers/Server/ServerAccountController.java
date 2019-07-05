package com.company.Controllers.Server;

import com.company.Controllers.JsonController;
import com.company.Models.User.Account;

import java.util.Comparator;

public class ServerAccountController {
    public static void saveAccounts() {
        JsonController.removeFile(Account.getSavedAccountsFilePath());
        JsonController.writeAllAccountsOnFile();
    }


    public static void sortPlayers(){
        Account.getAccounts().sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o2.getWins() - o1.getWins();
            }
        });
    }
}
