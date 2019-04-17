package com.company;

import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Minion.MinionType;
import com.company.Views.ConsoleInput;
import com.company.Views.ConsoleOutput;
import com.sun.java_cup.internal.runtime.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String command = scanner.nextLine();
        ConsoleInput.accountMenuCommandsChecker(command);
/*        Minion minion = new Minion(MinionType.KAMANDAR_FARS);
        String name = minion.getName();
        System.out.println(name);*/
    }
}
