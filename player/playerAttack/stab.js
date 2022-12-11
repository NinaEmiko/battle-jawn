import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";
import { userInterface } from "../../UI/UI.js";
import { logBoxDisplay } from "../../UI/logBox/logBoxDisplay.js";
import { enemyMoves } from "../../enemy/enemyMoves.js";

export function stab() {
    let damage = Math.floor(Math.random() * user.strength);

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        if (damage < user.strength / 2 && damage !== 0) {
            damage = Math.floor(damage + user.strength / 2);
        }

        let criticalHitChance = Math.floor(Math.random() * 100)
        
            if (criticalHitChance > 90) {
                damage = Math.floor(damage * 1.5);
                logBox.push("Critical Hit!");
            }

        createEnemy.health = createEnemy.health - damage * 1.1;
        logBox.push(`You used STAB. You did ${damage} damage.`);
        userInterface();
        logBoxDisplay();
        enemyMoves();
    } else {
        logBox.push(`You missed the enemy!`);
        userInterface();
        logBoxDisplay();
        enemyMoves();
    }
    //During real time combat, will have a 1 second cool down
}