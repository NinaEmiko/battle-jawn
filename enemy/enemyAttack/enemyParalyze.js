//Pauses player's ability to use a move

import { createEnemy } from '../../enemy/createEnemy.js';
import { logBox } from '../../UI/logBox/logBox.js';
import { logBoxDisplay } from '../../UI/logBox/logBoxDisplay.js';
import { enemySoulEater } from './enemySoulEater.js';
import { userInterface } from "../../UI/UI.js";
import{ isAlive } from "../../user/isAlive.js";

export function enemyParalyze() {
    let successRate = Math.floor(Math.random() * 100);

    let missed = false;
    if (successRate > 75) {missed = true};

    if (missed === false) {

        logBox.push(`${createEnemy.name} used PARALYZE! You cannot move!`); 
        setTimeout(userInterface, 1000);
        setTimeout(logBoxDisplay, 1000);
        isAlive();
        enemySoulEater();
    } else {
        logBox.push(`${createEnemy.name}'s PARALYZE missed you!`);
    }
}