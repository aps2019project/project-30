package com.company.Controllers.Server;

import com.company.Controllers.JsonController;
import com.company.Models.User.Account;

public class ServerAccountController {
    public static void saveAccounts() {
        JsonController.removeFile(Account.getSavedAccountsFilePath());
        JsonController.writeAllAccountsOnFile();
    }
}
