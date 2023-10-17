import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";
import { userInterface } from "../../UI/UI.js";
import { logBoxDisplay } from "../../UI/logBox/logBoxDisplay.js";
import { enemyMoves } from "../../enemy/enemyMoves.js";

export function impale() {
    let damage = user.strength * 1.2;

    let criticalHitChance = Math.floor(Math.random() * 100)
    
        if (criticalHitChance > 90) {
            damage = Math.floor(damage * 1.5);
            logBox.push("Critical Hit!");
        }

    createEnemy.health = createEnemy.health - damage;
    logBox.push(`You used Impale! You did ${damage} damage.`);
    userInterface();
    logBoxDisplay();
    enemyMoves();
    //During real time combat, will have a 3 second cool down
}