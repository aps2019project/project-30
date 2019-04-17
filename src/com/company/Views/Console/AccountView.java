package com.company.Views.Console;

import com.company.Models.User.Account;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;

public class AccountView {

    public static void printLeaderBoard(ArrayList<Account> leaderBoard) {
        for (int i = 0; i < leaderBoard.size(); i++) {
            System.out.println((i + 1) + "- Username : " + leaderBoard.get(i).getUsername() + " - Wins : " + leaderBoard.get(i).getWins());
        }
    }

    public static void printAccountCommandsToHelp() {
        ConsoleOutput.printMessage("##### Account Commands #####");
        ConsoleOutput.printMessage("1- create account [username] : to creating new account");
        ConsoleOutput.printMessage("2- login [username] : to logging in to existing account");
        ConsoleOutput.printMessage("3- show leaderboard : to show ranking of accounts");
        ConsoleOutput.printMessage("4- save : to save!");
        ConsoleOutput.printMessage("5- logout : obviously to logging out!");
    }
}
