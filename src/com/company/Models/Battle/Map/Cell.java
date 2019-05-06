package com.company.Models.Battle.Map;

import com.company.Models.Buff.AntiBuff;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;
import com.company.Models.Card.Flag;
import com.company.Models.Card.Item.Item;
import com.company.Models.Card.Soldier;

import java.util.ArrayList;

public class Cell {
    private int xCoordinate, yCoordinate;
    private Card cardInCell;
    private Item item;
    private ArrayList<Buff> cellEffects = new ArrayList<>();
    private Flag flag;

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

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

    public void throwBuffsToSoldier() {
        int buffsCastedSizeBeforeThrow = cardInCell.getBuffsCasted().size();
        for (Buff buff : cardInCell.getBuffsToCast()) {
            Buff clonedBuff = buff.clone();
            clonedBuff.setCardToCast(cardInCell);
            cardInCell.getBuffsCasted().add(clonedBuff);
        }
        for (int i = 0; i < cardInCell.getBuffsCasted().size(); i++) {
            Buff buff = cardInCell.getBuffsCasted().get(i);
            if (buff instanceof AntiBuff || i > buffsCastedSizeBeforeThrow)
                buff.cast();
        }
    }
}
