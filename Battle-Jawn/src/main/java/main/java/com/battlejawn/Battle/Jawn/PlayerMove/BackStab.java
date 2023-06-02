package main.java.com.battlejawn.Battle.Jawn.PlayerMove;

public class BackStab implements CriticalHit, Attack {

    private int damage;
    private StatusAilment StatusAilment;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public StatusAilment getStatusAilment() {
        return StatusAilment;
    }

    public void setStatusAilment(StatusAilment statusAilment) {
        StatusAilment = statusAilment;
    }

    public attack() {
        setDamage(player.strength * 2);

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
