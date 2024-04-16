import { strike } from "../../player/playerAttack/strike.js";
import { playerHeal } from "../playerHeal/playerHeal.js";
import { playerRun } from "../playerRun.js";
import { disableButtons } from "../../UI/disableButtons.js";
import { impale } from "../../player/playerAttack/impale.js";

export function tankMove(input) {
    if (input === 'button1') {
        strike();
        disableButtons();
    } else if (input === 'button2') {
        playerHeal();
        disableButtons();
    } else if (input === 'button3') {
        impale();
        disableButtons();
    } else if (input === 'button4') {
        playerRun();
        disableButtons();
    }
}