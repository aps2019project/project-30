package com.company.Models.Card;

import com.company.Models.Battle.Map.Cell;
import com.company.Models.User.Player;

public class Flag {
    private Player flagHolder1;
    private Cell cell;

    public Player getFlagHolder() {
        return flagHolder1;
    }

    public void setFlagHolder(Player flagHolder) {
        this.flagHolder1 = flagHolder;
    }

    public Cell getCell(){
        return cell;
    }

}
