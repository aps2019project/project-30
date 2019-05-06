package com.company.Models.Battle.Modes;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.BattleLog;
import com.company.Models.Card.Card;
import com.company.Models.Card.Flag;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.User.Player;

import java.util.HashMap;
import java.util.Map;

public enum Mode {
    CAPTURE_THE_FLAG {
        @Override
        public Player getWinner() {
            if(getBattle().getTurn() - getBattle().getFlags().get(0).getHoldingTurn() == 6
                    && getBattle().getFlags().get(0).getHoldingTurn() != 0){
                Card card=getBattle().getFlags().get(0).getFlagHolder();
                if(getBattle().getPlayers()[0].getUsedCards().contains(card)){
                    return getBattle().getPlayers()[0];
                }
                else
                    return getBattle().getPlayers()[1];
            }
            return null;
        }
    },
    COLLECTING_FLAGS {
        @Override
        public Player getWinner() {
            final int FLAGS = getBattle().getFlags().size();
            int player1FlagCounter = 0, player2FlagCounter = 0;
            for (Flag flag : Battle.getPlayingBattle().getFlags()) {
                if (flag.getFlagHolder() != null) {
                    if (BattleController.playerThatHasThisCard(flag.getFlagHolder()).equals(getBattle().getPlayers()[0])) {
                        player1FlagCounter++;
                    } else {
                        player2FlagCounter++;
                    }
                }
            }
            if (player1FlagCounter > FLAGS / 2) {
                return getBattle().getPlayers()[0];
            } else if (player2FlagCounter > FLAGS / 2) {
                return getBattle().getPlayers()[1];
            }
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
