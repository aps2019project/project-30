package com.company.Controllers;

import com.company.Models.Card.Card;
import com.company.Models.ErrorType;
import com.company.Models.Shop;
import com.company.Models.User.Account;
import com.company.Views.ConsoleOutput;

public class ShopController {

    public static void search(String cardName) {
        System.out.println(Shop.getCardIdByName(cardName));
    }

    public static void buy(Account account, Card card) {
        //add to account collection
        account.decrementDrake(card.getPriceInDrake());
    }


    public static void sell(Account account, Card card) {
        //remove from account collection
        account.incrementDrake(card.getPriceInDrake());
    }
}
