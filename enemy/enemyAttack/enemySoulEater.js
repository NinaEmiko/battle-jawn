//33% chance
//Can only use if player is paralyzed
//Removes paralyze effect

import { createEnemy } from '../../enemy/createEnemy.js';
import { user } from  '../../user/user.js';
import { logBox } from '../../UI/logBox/logBox.js';

export function enemySoulEater() {
    let damage = 20;

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        user.health = user.health - damage;
        createEnemy.health = createEnemy.health + damage;
        logBox.push(`${createEnemy.name} used SOUL EATER! ${createEnemy.name} did ${damage} damage.`); 
    } else {
        logBox.push(`${createEnemy.name}'s SOUL EATER missed you!`);
    }
}