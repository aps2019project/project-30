package com.company.Views;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.ErrorType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ConsoleOutput {
    public static void printErrorMessage(ErrorType errorType) {
        System.out.println(errorType.getMessage());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void showAllCards(List<Card> cards) {
        Hero.showHeroes(cards);
        Item.showItems(cards);
        Card.showCards(cards);
    }

    public static void writeTextOnFile(String destinationAddress, String text) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(destinationAddress, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printer = new PrintStream(fileOutputStream);
        printer.print(text);
        printer.flush();
        printer.close();
    }

}
