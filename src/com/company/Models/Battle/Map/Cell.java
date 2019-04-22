package com.company.Models.Battle.Map;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;

import java.util.ArrayList;

public class Cell {
    private int xCoordinate, yCoordinate;
    private Card cardInCell;
    private ArrayList<Buff> cellEffects;

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
