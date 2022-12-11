import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";
import { userInterface } from "../../UI/UI.js";
import { logBoxDisplay } from "../../UI/logBox/logBoxDisplay.js";
import { enemyMoves } from '../../enemy/enemyMoves.js'

let button1 = document.getElementById('button1');

export function backStab() {
    button1.innerHTML = "Stab";
    let damage = user.strength * 2;

    let criticalHitChance = Math.floor(Math.random() * 100)
    
        if (criticalHitChance > 90) {
            damage = Math.floor(damage * 1.5);
            logBox.push("Critical Hit!");
        }

    createEnemy.health = createEnemy.health - damage;
    logBox.push(`You used Backstab! You did ${damage} damage.`);
    userInterface();
    logBoxDisplay();
    enemyMoves();

    //Next enemy attack will miss
    //Is unlocked after 3 consecutive successful stabs
}