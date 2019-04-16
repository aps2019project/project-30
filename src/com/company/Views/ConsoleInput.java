package com.company.Views;

import java.util.Scanner;

public class ConsoleInput {
    private static Scanner scanner = new Scanner(System.in);

    enum Menu {MAIN, ACCOUNT, COLLECTION, SHOP, BATTLE}

    Menu menu;
    public static void mainMenuCommandsChecker(String command) {

    }

    public static void collectionMenuCommandsChecker(String command) {

    }

    public static void accountMenuCommandsChecker(String command) {
        if(command.matches("creat username \\w+")){
            //creat account
        }else if(command.matches("login \\w+")){
            //login
        }else if(command.matches("show leaderboard")){
            //show leaderboard
        }else if(command.matches("save")){
            //save
        }else if(command.matches("logout")){
            //logout
        }else if(command.matches("buy \\d+")){
            //buy
        }else if(command.matches("help")){
            //help
        }
    }

    public static void shopMenuCommandsChecker(String command) {
        if(command.matches("show")){
            //show
        }else if(command.matches("help")){
            //help
        }else if(command.matches("exit")){
            //exit
        }else if(command.matches("search \\d+")){
            //search
        }else if(command.matches("show collection")){
            //show collection
        }else if(command.matches("search collection")){
            //search collection
        }else if(command.matches("buy \\d+")){
            //buy
        }else if(command.matches("sell \\d+")){
            //sell
        }
    }

    public static void battleMenuCommandsChecker() {

    }
}
