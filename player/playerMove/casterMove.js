import { playerHeal } from '../playerHeal/playerHeal.js';
import { wand } from "../playerAttack/wand.js";
import { blast } from '../playerAttack/blast.js';
import { playerRun } from '../playerRun.js';
import { disableButtons } from '../../UI/disableButtons.js';

export function casterMove(input) {
    if (input === 'button1') {
        wand();
        disableButtons();
    } else if (input === 'button2') {
        playerHeal();
        disableButtons();
    } else if (input === 'button3') {
        blast();
        disableButtons();
    } else if (input === 'button4') {
        playerRun();
        disableButtons();
    }
}