package com.company.Views.Console;

import com.company.Models.User.Account;

import java.util.ArrayList;

public class AccountView {
    public static void printLeaderBoard(ArrayList<Account> leaderBoard) {
        for (int i = 0; i < leaderBoard.size(); i++) {
            System.out.println((i + 1) + "- Username : " + leaderBoard.get(i).getUsername() + " - Wins : " + leaderBoard.get(i).getWins());
        }
    }
}
