import { stab } from "../playerAttack/stab.js";
import { playerHeal } from "../playerHeal/playerHeal.js";
import { playerSteal } from "../playerSteal.js";
import { playerRun } from "../playerRun.js";
import { disableButtons } from "../../UI/disableButtons.js";

export function dpsMove(input) {
    if (input === 'button1') {
        stab();
        disableButtons();
    } else if (input === 'button2') {
        playerHeal();
        disableButtons();
    } else if (input === 'button3') {
        playerSteal();
        disableButtons();
    } else if (input === 'button4') {
        playerRun();
        disableButtons();
    }
}