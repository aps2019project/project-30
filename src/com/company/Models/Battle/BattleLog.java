package com.company.Models.Battle;

import com.company.Models.User.Account;

public class BattleLog {
    private Account Account1;
    private Account Account2;
    private Account winnerAccount;
    private int battleTime;

    public BattleLog(Account account1, Account account2, Account winnerAccount, int battleTime) {
        Account1 = account1;
        Account2 = account2;
        this.winnerAccount = winnerAccount;
        this.battleTime = battleTime;
    }

    public Account getAccount1() {
        return Account1;
    }

    public void setAccount1(Account Account1) {
        this.Account1 = Account1;
    }

    public Account getAccount2() {
        return Account2;
    }

    public void setAccount2(Account Account2) {
        this.Account2 = Account2;
    }

    public Account getWinnerAccount() {
        return winnerAccount;
    }

    public void setWinnerAccount(Account winnerAccount) {
        this.winnerAccount = winnerAccount;
    }

    public int getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(int battleTime) {
        this.battleTime = battleTime;
    }
}
