package com.company;

import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Minion.MinionType;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Minion minion = new Minion(MinionType.KAMANDAR_FARS);
        String name = minion.getName();
        System.out.println(name);
    }
}
