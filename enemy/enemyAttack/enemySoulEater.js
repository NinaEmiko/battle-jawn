//33% chance
//Can only use if player is paralyzed
//Removes paralyze effect

import { createEnemy } from '../../enemy/createEnemy.js';
import { user } from  '../../user/user.js';
import { logBox } from '../../UI/logBox/logBox.js';
import { userInterface } from '../../UI/UI.js';
import { logBoxDisplay } from '../../UI/logBox/logBoxDisplay.js';

export function enemySoulEater() {
    let damage = 20;

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        user.health = user.health - damage;
        createEnemy.health = createEnemy.health + damage;
        setTimeout(userInterface, 1000);
        setTimeout(logBoxDisplay, 1000);
        logBox.push(`${createEnemy.name} used SOUL EATER! SOUL EATER did ${damage} damage and replenished ${damage} of ${createEnemy.name}'s health!`); 
    } else {
        logBox.push(`${createEnemy.name}'s SOUL EATER missed you!`);
    }
}