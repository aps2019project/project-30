package com.company.Views;

import java.util.Scanner;

public class ConsoleInput {
    private static Scanner scanner = new Scanner(System.in);

    enum Menu {MAIN, ACCOUNT, COLLECTION, SHOP, BATTLE}

    Menu menu;
    private static String command;

    public static void mainMenuCommandsChecker() {

    }

    public static void collectionMenuCommandsChecker() {

    }

    public static void accountMenuCommandsChecker() {

    }

    public static void shopMenuCommandsChecker() {
        command = scanner.nextLine();
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

        }else if(command.matches("sell \\d+")){

        }
    }

    public static void battleMenuCommandsChecker() {

    }
}
