package com.company.Views;

import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.Shop;

public class ShopView {

    public static void showAll() {
        if (Shop.getShopCollection() != null && !Shop.getShopCollection().getCards().isEmpty()) {
            for (int i = 0; i < 3; i++) {//one time for heroes, once for itmes and once for cards(spells and minions)
                if (i == 0) {
                    showHeroes();
                } else if (i == 1) {
                    //showing items
                } else if (i == 2) {
                    showCards();
                }
            }
        }
    }

    private static void showHeroes() {
        System.out.println("Heroes :");
        for (int i = 0; i < Shop.getShopCollection().getCards().size(); i++) {
            System.out.println((i + 1) + "- Name : " + Shop.getShopCollection().getCards().get(i).getName() +
                    "- AP : " + ((Hero) Shop.getShopCollection().getCards().get(i)).getHeroType().getAttackPower() +
                    "- HP : " + ((Hero) Shop.getShopCollection().getCards().get(i)).getHealth() + /*special power*/
                    "- buy cost : " + Shop.getShopCollection().getCards().get(i).getPriceInDrake());
        }
    }

    private static void showCards() {
        System.out.println("Cards :");
        for (int i = 0; i < Shop.getShopCollection().getCards().size(); i++) {
            if (Shop.getShopCollection().getCards().get(i) instanceof Minion) {
                System.out.println((i + 1) + "- Type : Minion " +
                        "- Name : " + Shop.getShopCollection().getCards().get(i).getName() +
                        "- AP : " + ((Minion) Shop.getShopCollection().getCards().get(i)).getMinionType().getAttackPower() +
                        "- HP : " + ((Minion) Shop.getShopCollection().getCards().get(i)).getHealth() +
                        "- MP : " + Shop.getShopCollection().getCards().get(i).getManaPoint() + /*special power*/
                        "- buy cost : " + Shop.getShopCollection().getCards().get(i).getPriceInDrake());
            } else if (Shop.getShopCollection().getCards().get(i) instanceof Spell) {
                System.out.println((i + 1) + "- Type : Spell " +
                        "- Name : " + Shop.getShopCollection().getCards().get(i).getName() +
                        "- MP : " + Shop.getShopCollection().getCards().get(i).getManaPoint() +
                        "- Desc : " + Shop.getShopCollection().getCards().get(i).getDescription() +
                        "- buy cost : " + Shop.getShopCollection().getCards().get(i).getPriceInDrake());
            }
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
