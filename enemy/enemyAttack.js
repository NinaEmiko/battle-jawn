import { createEnemy } from '../enemy/createEnemy.js';
import { user } from '../user/user.js';
import { logBox } from '../logBox/logBox.js';

export const enemyAttack = () => {
    if (createEnemy.health > 0) {
        let damage = Math.floor(Math.random() * createEnemy.strength);
        user.health = user.health - damage;
        logBox.push(`${createEnemy.name} has attacked! ${createEnemy.name} did ${damage} damage.`);
    } else {
        logBox.push(`You have vanquished the ${createEnemy.name}!`);
        setTimeout(function() {window.location.reload();
        }, 4000);
    }
}