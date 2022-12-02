import { createEnemy } from '../enemy/createEnemy.js';
import { user } from '../user/user.js';
import { logBox } from '../logBox/logBox.js';
import { userInterface } from '../user/UI.js';
import { logBoxDisplay } from '../logBox/logBoxDisplay.js';

export const enemyAttack = () => {
    if (createEnemy.health > 0) {
        let damage = Math.floor(Math.random() * createEnemy.strength);
        user.health = user.health - damage;
        logBox.push(`${createEnemy.name} has attacked! ${createEnemy.name} did ${damage} damage.`);
        userInterface();
        logBoxDisplay();
    } else {
        logBox.push(`You have vanquished the ${createEnemy.name}!`);
        userInterface();
        setTimeout(function() {window.location.reload();
        }, 4000);
    }
}