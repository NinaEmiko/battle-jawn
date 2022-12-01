import { playerAttack } from '../player/playerAttack.js';
import { playerHeal } from '../player/playerHeal.js';
import { playerRun } from '../player/playerRun.js';
import { playerSteal } from '../player/playerSteal.js';
import { enemyAttack } from '../enemy/enemyAttack.js';
import { userInterface } from '../user/UI.js'
import { logBoxDisplay } from '../logBox/logBoxDisplay.js';

export const playerMove = (input) => {

    if (input === 'attack') {
        playerAttack();
    } else if (input === 'heal') {
        playerHeal();
    } else if (input === 'run') {
        playerRun();
    } else if (input === 'steal') {
        playerSteal();
    }

    setTimeout(enemyAttack, 250);
    setTimeout(userInterface, 250);
    setTimeout(logBoxDisplay, 250);
}