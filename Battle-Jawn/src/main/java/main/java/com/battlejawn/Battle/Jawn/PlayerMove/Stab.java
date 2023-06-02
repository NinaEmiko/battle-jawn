package main.java.com.battlejawn.Battle.Jawn.PlayerMove;

public class Stab implements CriticalHit, Missable {

    private int damage;
    private int stabCount;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getStabCount() {
        return stabCount;
    }

    public void setStabCount(int stabCount) {
        this.stabCount = stabCount;
    }

    public attack() {
        setDamage(Math.floor(Math.random() * user.strength));

        if (miss()) {
            setDamage(0);
        } else if (criticalHit()){
            setDamage(damage *= 1.5);
        }
        setStabCount(stabCount++);
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
