import { playerAttack } from '../player/playerAttack.js';
import { playerHeal } from '../player/playerHeal.js';
import { playerRun } from '../player/playerRun.js';
import { playerSteal } from '../player/playerSteal.js';
import { isAlive } from '../user/isAlive.js';

export const playerMove = (input) => {

    if (input === 'attack') {
        isAlive();
        playerAttack();
    } else if (input === 'heal') {
        isAlive();
        playerHeal();
    } else if (input === 'run') {
        isAlive();
        playerRun();
    } else if (input === 'steal') {
        isAlive();
        playerSteal();
    }
}