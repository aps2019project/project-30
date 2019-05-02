package com.company.Models.Battle.Modes;

import com.company.Models.Battle.Battle;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.User.Player;

import java.util.HashMap;
import java.util.Map;

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
            Map<Player, Hero> playerHeroMap = new HashMap<>();
            playerHeroMap.put(getBattle().getPlayers()[0] ,(Hero) getBattle().getPlayers()[0].getDeck().getHeroCard());
            playerHeroMap.put(getBattle().getPlayers()[1] ,(Hero) getBattle().getPlayers()[1].getDeck().getHeroCard());
            for (Map.Entry<Player, Hero> playerHeroEntry : playerHeroMap.entrySet()) {
                if (playerHeroEntry.getValue().getHealth() <= 0) {
                    return playerHeroEntry.getKey();
                }
            }
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
