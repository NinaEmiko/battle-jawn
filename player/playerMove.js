import { playerAttack } from './playerAttack/playerAttack.js';
import { playerHeal } from '../player/playerHeal/playerHeal.js';
import { playerRun } from '../player/playerRun.js';
import { playerSteal } from '../player/playerSteal.js';
import { isAlive } from '../user/isAlive.js';
import { disableButtons } from '../UI/disableButtons.js';

export const playerMove = (input) => {

    if (input === 'attack') {
        isAlive();
        playerAttack();
        disableButtons();
    } else if (input === 'heal') {
        isAlive();
        playerHeal();
        disableButtons();
    } else if (input === 'run') {
        isAlive();
        playerRun();
        disableButtons();
    } else if (input === 'steal') {
        isAlive();
        playerSteal();
        disableButtons();
    }
}