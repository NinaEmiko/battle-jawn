import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";
import { userInterface } from "../../UI/UI.js";
import { logBoxDisplay } from "../../UI/logBox/logBoxDisplay.js";
import { enemyMoves } from "../../enemy/enemyMoves.js";

export function blast() {
    let damage = (Math.floor(Math.random() * user.strength) + user.strength / 4);

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {

        let criticalHitChance = Math.floor(Math.random() * 100)
        
            if (criticalHitChance > 90) {
                damage = Math.floor(damage * 1.5);
                logBox.push("Critical Hit!");
            }

        createEnemy.health = createEnemy.health - damage;
        user.health = user.health - 10;
        logBox.push(`You used Blast! You did ${damage} damage. Blast did 10 damage to you.`);
        userInterface();
        logBoxDisplay();
        enemyMoves();
    } else {
        logBox.push(`You missed the enemy!`);
        userInterface();
        logBoxDisplay();
        enemyMoves();
    }
    //Next attack on enemy will += 5
    //During real time combat, will have a 3.5 second cool down
}