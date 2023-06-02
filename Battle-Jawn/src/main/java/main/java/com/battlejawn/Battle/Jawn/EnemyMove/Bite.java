package main.java.com.battlejawn.Battle.Jawn.EnemyMove;

import main.java.com.battlejawn.Battle.Jawn.PlayerMove.CriticalHit;
import main.java.com.battlejawn.Battle.Jawn.PlayerMove.Missable;

public class Bite implements CriticalHit, Missable {
    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public attack() {
        setDamage(Math.floor(Math.random() * enemy.strength));

        if (miss()) {
            setDamage(0);
        } else if (criticalHit()){
            setDamage(damage *= 1.5);
        }
    }

    public boolean criticalHit() {
        int chance = Math.floor(Math.random() * 100);
        if (chance > 90) {
            return true;
        }
        return false;
    }

    public boolean miss() {
        int chance = Math.floor(Math.random() * 100);
        if (chance > 95) {
            return true;
        }
        return false;
    }
}
