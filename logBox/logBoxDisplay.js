import { logBox } from "./logBox.js";

const logBoxDisplayElement = document.getElementById('logBoxDisplayElement');

export function logBoxDisplay() {

    for (let i = 0; i < logBox.length; i++) {
        logBoxDisplayElement.innerHTML=logBox[i];
    }
}