package com.company.Models.Battle;

import com.company.Models.Battle.Modes.Mode;

public enum BattleType {
    STORY {
        public int getWinningPrize(int level) {
            int BASE_PRIZE = 500;
            return BASE_PRIZE * level;
        }
        public Mode getMode(int level) {
            switch (level) {
                case 1:
                    return Mode.KILLING_GENERAL;
                case 2:
                    return Mode.CAPTURE_THE_FLAG;
                case 3:
                    return Mode.COLLECTING_FLAGS;
                default:
                    return Mode.KILLING_GENERAL;
            }
        }
    },
    CUSTOM,
    MULTI;

    private int winningPrize;
}
