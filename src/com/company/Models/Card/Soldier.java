package com.company.Models.Card;

import com.company.Controllers.BattleController;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Map.Cell;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.Buff.AntiBuff;
import com.company.Models.Buff.Buff;
import com.company.Models.Card.Minion.ActivationTime;
import com.company.Models.Card.Minion.Minion;


public class Soldier extends Card {
    private Cell cell;
    private AttackType attackType;
    private int fullHealth;
    private int health;
    private int areaOfEffect;
    private int attackPower;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public int getFullHealth() {
        return fullHealth;
    }

    public void setFullHealth(int fullHealth) {
        this.fullHealth = fullHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setAreaOfEffect(int areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void incrementHealth(int health) {
        this.health += health;
    }

    public void decrementHealth(int health) {
        this.health -= health;
        deathHandler();
    }

    private void deathHandler() {
        if (isDead()) {
            if (this instanceof Minion && ((Minion) this).getActivationTime() == ActivationTime.ON_SPAWN) {
                Battle.getPlayingBattle().getBattleController().throwAttackerCardBuffstoTargetCard(this,
                        Battle.getPlayingBattle().getBattleController().getEenmyPlayer(
                                BattleController.playerThatHasThisCard(this)).getDeck().getHeroCard());
            }
            if (Battle.getPlayingBattle().getMode() == Mode.CAPTURE_THE_FLAG) {
                Flag flag = Battle.getPlayingBattle().getFlags().get(0);
                if (this.equals(flag.getFlagHolder())) {
                    flag.setCell(this.getCell());
                    flag.setHoldingTurn(0);
                    this.getCell().setFlag(flag);
                }
            }
            this.getCell().setCardInCell(null);
            this.setCell(null);
            Battle.getPlayingBattle().getBattleController().getGraveYard().add(this);
        }
    }

    public void incrementAttackPower(int attackPower) {
        this.attackPower += attackPower;
    }

    public void decrementAttackPower(int attackPower) {
        this.attackPower -= attackPower;
    }

    public void attack(Card targetCard, Boolean isCombo) {
        if (!hasBuffByName(Buff.Name.STUN)) {
            ((Soldier) targetCard).decrementHealth(getAttackPower());
            if (!isCombo) {
                ((Soldier) targetCard).counterAttack(this);
            }
            //todo holy buff yaroo ejra she
            //TODO Check Counter Buffs
        }
    }

    public void counterAttack(Card targetCard) {
        if (!hasBuffByName(Buff.Name.DISARM)) {
            ((Soldier) targetCard).decrementHealth(getAttackPower());
        }
    }

    public boolean hasBuffByName(Buff.Name buffName) {
        for (Buff buff : getBuffsCasted()) {
            if (buff.getName().equals(buffName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDead() {
        if (this.health <= 0)
            return true;
        return false;
    }
}
