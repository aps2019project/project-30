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
            for (Player player : getBattle().getPlayers()) {
                playerHeroMap.put(player ,(Hero) player.getDeck().getHeroCard());
            }
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
