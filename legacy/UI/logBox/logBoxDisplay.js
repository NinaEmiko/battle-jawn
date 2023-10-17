import { logBox } from "./logBox.js";

const logBoxDisplayElement = document.getElementById('logBoxDisplayElement');

export function logBoxDisplay() {
    let display = "";
    logBox.forEach(element => display += ` ${element} <br>`)
    logBoxDisplayElement.innerHTML=display;
}