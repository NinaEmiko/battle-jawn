import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";

export function backStab() {
    let damage = user.strength * 2;

    let criticalHitChance = Math.floor(Math.random() * 100)
    
        if (criticalHitChance > 90) {
            damage = Math.floor(damage * 1.5);
            logBox.push("Critical Hit!");
        }

    createEnemy.health = createEnemy.health - damage;
    logBox.push(`You used Backstab! You did ${damage} damage.`);
    //Next enemy attack will miss
    //Is unlocked after 3 consecutive successful stabs
}