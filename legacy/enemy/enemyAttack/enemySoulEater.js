import { createEnemy } from '../createEnemy.js';
import { user } from  '../../user/user.js';
import { logBox } from '../../UI/logBox/logBox.js';

export function enemySoulEater() {
    let damage = 20;

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        user.health = user.health - damage;
        createEnemy.health = createEnemy.health + damage;
        logBox.push(`${createEnemy.name} used SOUL EATER! SOUL EATER did ${damage} damage and replenished ${damage} of ${createEnemy.name}'s health!`); 
    } else {
        logBox.push(`${createEnemy.name}'s SOUL EATER missed you!`);
    }
}