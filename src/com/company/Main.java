package com.company;

import com.company.Controllers.ShopController;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Minion.MinionType;
import com.company.Views.ConsoleInput;
import com.company.Views.ConsoleOutput;
import com.sun.java_cup.internal.runtime.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Minion minion = new Minion(MinionType.KAMANDAR_FARS);
        String name = minion.getName();
        System.out.println(name);
    }
}
