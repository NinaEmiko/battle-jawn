import { disableButtons } from "../UI/disableButtons.js";
import { Tank } from '../player/role/tank.js';
import { Healer } from '../player/role/healer.js';
import { DPS } from '../player/role/dps.js';
import { Caster } from '../player/role/caster.js';
import { statusAilments } from '../player/statusAilments.js';

export function playerSelection(input) {
    let createUser;

    if (input === 'button1') {
        localStorage.setItem('User Role', 'Tank')
        disableButtons();
        window.location.href = "../index.html";
    } else if (input === 'button2') {
        localStorage.setItem('User Role', 'Healer')
        disableButtons();
        window.location.href = "../index.html";
    } else if (input === 'button3') {
        localStorage.setItem('User Role', 'DPS')
        disableButtons();
        window.location.href = "../index.html";
    } else if (input === 'button4') {
        localStorage.setItem('User Role', 'Caster')
        disableButtons();
        window.location.href = "../index.html";
    }
    return createUser;
}