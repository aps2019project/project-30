package com.company.Views;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Shop;

public class ShopView {

    private void showHeroes() {
        System.out.println("Heroes :");
        for (int i = 0; i < Shop.getShopCollection().getCards().size(); i++) {
            System.out.println((i + 1) + "- Name : " + Shop.getShopCollection().getCards().get(i).getName() + /*AP*/
                    "- HP : " + ((Hero) Shop.getShopCollection().getCards().get(i)).getHealth() + /*special power*/
                    "- buy cost : " + Shop.getShopCollection().getCards().get(i).getPriceInDrake());
        }
    }

    public static void printShopCommandsToHelp() {
        System.out.println("##### Shop commands #####");
        System.out.println("1- exit : to exit");
        System.out.println("2- search [item name | card name] : to searching in collectionShop");
        System.out.println("3- search collection [item name | card name] : to searching in your collection");
        System.out.println("4- buy [item name | card name] : to buying cards or itmes");
        System.out.println("5- sell [item name | card name] : to selling cards or itmes");
        System.out.println("6- show : to showing cards and items that are exist in shop");
    }
}
