package com.company.Views;

import com.company.Models.Card.Card;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;
import com.company.Models.User.Account;

import java.util.ArrayList;

public class ShopView {

    public static void showAllCardsInShop() {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            ConsoleOutput.showAllCards(Shop.getShopCollection().getCards());
        }
    }

    public static void printShopCommandsToHelp() {
        System.out.println("##### Shop commands #####");
        System.out.println("1- exit : to exit");
        System.out.println("2- searchButton [item name | card name] : to searching in collectionShop");
        System.out.println("3- searchButton collection [item name | card name] : to searching in your collection");
        System.out.println("4- buy [item name | card name] : to buying cards or itmes");
        System.out.println("5- sell [item name | card name] : to selling cards or itmes");
        System.out.println("6- showAllCardsInCollection : to showing cards and items that are exist in shop");
    }
}
