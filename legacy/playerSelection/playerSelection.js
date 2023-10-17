import { disableButtons } from "../UI/disableButtons.js";

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