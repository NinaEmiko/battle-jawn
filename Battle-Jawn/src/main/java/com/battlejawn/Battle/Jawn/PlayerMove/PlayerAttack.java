package main.java.com.battlejawn.Battle.Jawn.PlayerMove;

import main.java.com.battlejawn.Battle.Jawn.StatusAilments.StatusAilments;

public class PlayerAttack {

    public int damage(int strength) {
        return Math.floor(Math.random() * strength);
    }

    public boolean stagger() {
        if (Math.floor(Math.random() * 10) < 3) {
            return true;
        }
        return false;
    }

    public boolean missed() {
        if (Math.floor(Math.random() * 10) < 1) {
            return true;
        }
        return false;
    }

    public boolean criticalHit() {
        if (Math.floor(Math.random() * 100) < 10) {
            return true;
        }
        return false;
    }

}
