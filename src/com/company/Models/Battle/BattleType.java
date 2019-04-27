package com.company.Models.Battle;

public enum BattleType {
    STORY {
        public int getWinningPrize(int level) {
            int BASE_PRIZE = 500;
            return BASE_PRIZE * level;
        }
    },
    CUSTOM,
    MULTI;

    private int winningPrize;
}
