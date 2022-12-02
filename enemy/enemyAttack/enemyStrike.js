import { createEnemy } from '../../enemy/createEnemy.js';
import { user } from  '../../user/user.js';
import { logBox } from '../../UI/logBox/logBox.js';

export function enemyStrike() {
    let damage = Math.floor(Math.random() * createEnemy.strength);
    user.health = user.health - damage;

    if (damage > 0) {
        logBox.push(`${createEnemy.name} has attacked! ${createEnemy.name} did ${damage} damage.`);
    } else {
        logBox.push(`${createEnemy.name}'s strike missed you!`);
    }
}