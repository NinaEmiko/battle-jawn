import { disableButtons } from "../../UI/disableButtons.js";
import { playerHeal } from "../playerHeal/playerHeal.js";
import { playerRun } from "../playerRun.js";
import { wand } from "../playerAttack/wand.js";

export function healerMove(input) {
    if (input === 'button1') {
        wand();
        disableButtons();
    } else if (input === 'button2') {
        playerHeal();
        disableButtons();
    } else if (input === 'button3') {
        holy();
        disableButtons();
    } else if (input === 'button4') {
        playerRun();
        disableButtons();
    }
}