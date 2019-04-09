package com.company.Models.Battle.Map;

import com.company.Models.Buff.Buff;
import com.company.Models.Card.Card;

public class Cell {
    private int xCoordinate, yCoordinate;
    private Card cardInCell;
    private Buff cellEffect;

    public int getxCoordinate() {
        return xCoordinate;
    }

    public Buff getCellEffect() {
        return cellEffect;
    }

    public void setCellEffect(Buff cellEffect) {
        this.cellEffect = cellEffect;
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
