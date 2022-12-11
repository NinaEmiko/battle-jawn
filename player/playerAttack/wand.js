import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";
import { userInterface } from "../../UI/UI.js";
import { logBoxDisplay } from "../../UI/logBox/logBoxDisplay.js";
import { enemyMoves } from "../../enemy/enemyMoves.js";

export function wand() {
    let damage = user.strength * .75;

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        createEnemy.health = createEnemy.health - damage;
        logBox.push(`You used WAND. You did ${damage} damage.`);
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