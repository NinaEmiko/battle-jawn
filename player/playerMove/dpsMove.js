import { stab } from "../playerAttack/stab.js";
import { playerHeal } from "../playerHeal/playerHeal.js";
import { playerSteal } from "../playerSteal.js";
import { playerRun } from "../playerRun.js";
import { disableButtons } from "../../UI/disableButtons.js";
import { backStab } from "../playerAttack/backStab.js";

let button1 = document.getElementById('button1');

export function dpsMove(input) {
    if (input === 'button1') {
        if (button1.innerHTML === 'Stab') {
            stab();
            disableButtons();
        } else {
            backStab();
            disableButtons();
        }
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