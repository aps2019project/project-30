package com.company.Models;

//import com.company.Controllers.ShopController;
import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.User.Account;
import com.company.Views.ConsoleOutput;

import java.util.ArrayList;

public class Shop {

    static Collection shopCollection;

    public static Collection getShopCollection() {
        return shopCollection;
    }

    public static ArrayList<Card> search(Account account, String name) {
        return null;
    }

    public static void buy(Account account, String name) {
/*        if (ShopController.cardExistsInShop(name)) {

        } else {
            //ConsoleOutput.printErrorMessage(ErrorType.CARD_NOTFOUND);
        }*/
    }

    public void sell(Account account, int id) {

    }

    public static void show() {

    }

    public static void showCollection() {

    }
}
