package com.company.Models.Battle;

import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Player;

public abstract class Battle {
    private Mode mode;
    private Player turnToPlay;


    public abstract void handler();
}
