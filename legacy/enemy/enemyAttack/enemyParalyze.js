//Pauses player's ability to use a move

import { createEnemy } from '../createEnemy.js';
import { logBox } from '../../UI/logBox/logBox.js';
import { user } from '../../user/user.js';

export function enemyParalyze() {
    let successRate = Math.floor(Math.random() * 100);

    let missed = false;
    if (successRate > 75) {missed = true};

    if (missed === false) {
        user.statusAilments.paralyze = true;
        logBox.push(`${createEnemy.name} used PARALYZE! You cannot move!`); 
    } else {
        logBox.push(`${createEnemy.name}'s PARALYZE missed you!`);
    }
}