package com.company.Models.Card;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.User.Player;


public class Flag {
    private Card flagHolder;
    private Cell cell;

    public Card getFlagHolder() {
        return flagHolder;
    }

    public void setFlagHolder(Card flagHolder) {
        this.flagHolder = flagHolder;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
