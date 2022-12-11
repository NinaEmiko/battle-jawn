//Pauses player's ability to use a move

import { createEnemy } from '../../enemy/createEnemy.js';
import { logBox } from '../../UI/logBox/logBox.js';
import { enemyAttack } from './enemyAttack.js';

export function enemyParalyze() {
    let successRate = Math.floor(Math.random() * 100);

    let missed = false;
    if (successRate > 75) {missed = true};

    if (missed === false) {

        logBox.push(`${createEnemy.name} used PARALYZE! ${createEnemy.name} did ${damage} damage.`); 
        enemyAttack();
    } else {
        logBox.push(`${createEnemy.name}'s PARALYZE missed you!`);
    }
}