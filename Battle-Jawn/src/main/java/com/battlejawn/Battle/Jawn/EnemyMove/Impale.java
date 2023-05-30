package main.java.com.battlejawn.Battle.Jawn.EnemyMove;

public class Impale implements CriticalHit {
    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public attack() {
        setDamage(enemy.strength * 1.2);

        if (criticalHit()){
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
}
