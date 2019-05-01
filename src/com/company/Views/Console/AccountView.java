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
        System.out.println("##### Account Commands #####");
        System.out.println("1- create account [username] : to creating new account");
        System.out.println("2- login [username] : to logging in to existing account");
        System.out.println("3- show leader board : to show ranking of accounts");
        System.out.println("4- save : to save!");
        System.out.println("5- logout : obviously to logging out!");
    }
}
