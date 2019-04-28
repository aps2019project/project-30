package com.company.Models.Battle.Map;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Item.Item;

import java.util.ArrayList;

public class Cell {
    private int xCoordinate, yCoordinate;
    private Card cardInCell;
    private Item item;
    private ArrayList<Buff> cellEffects;

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public ArrayList<Buff> getCellEffect() {
        return cellEffects;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Card getCardInCell() {
        return cardInCell;
    }

    public void setCardInCell(Card cardInCell) {
        this.cardInCell = cardInCell;
    }
}
