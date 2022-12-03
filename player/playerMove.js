import { playerAttack } from './playerAttack/playerAttack.js';
import { playerHeal } from '../player/playerHeal/playerHeal.js';
import { playerRun } from '../player/playerRun.js';
import { playerSteal } from '../player/playerSteal.js';
import { disableButtons } from '../UI/disableButtons.js';
import { user } from '../user/user.js';
import { deathDisableButtons } from '../UI/deathDisableButtons.js';

export const playerMove = (input) => {

    if (user.health > 0) {
        if (input === 'attack') {
            playerAttack();
            disableButtons();
        } else if (input === 'heal') {
            playerHeal();
            disableButtons();
        } else if (input === 'run') {
            playerRun();
            disableButtons();
        } else if (input === 'steal') {
            playerSteal();
            disableButtons();
        }
    } else {
        deathDisableButtons();
    }
}