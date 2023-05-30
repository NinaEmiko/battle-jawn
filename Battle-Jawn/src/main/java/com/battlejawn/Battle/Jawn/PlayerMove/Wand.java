package main.java.com.battlejawn.Battle.Jawn.PlayerMove;

public class Wand implements Missable {

    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public attack() {
        setDamage(Math.floor(user.strength * .75));

        if (miss()) {
            setDamage(0);
        } else if (criticalHit()){
            setDamage(damage *= 1.5);
        }
    }

    public boolean miss() {
        int chance = Math.floor(Math.random() * 100);
        if (chance > 95) {
            return true;
        }
        return false;
    }
}
