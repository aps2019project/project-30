package com.company.Models.Battle.Modes;

import com.company.Models.Battle.Battle;
import com.company.Models.User.Player;

public enum Mode {
    CAPTURE_THE_FLAG {
        @Override
        public Player getWinner() {
            return null;
        }
    },
    COLLECTING_FLAGS {
        @Override
        public Player getWinner() {
            return null;
        }
    },
    KILLING_GENERAL {
        @Override
        public Player getWinner() {
            return null;
        }
    };

    private Battle battle;

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

    public abstract Player getWinner();
}
