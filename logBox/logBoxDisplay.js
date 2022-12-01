import { logBox } from "./logBox.js";

const logBoxDisplayElement = document.getElementById('logBoxDisplayElement');
//logBoxDisplayElement.scrolltop = logBoxDisplayElement.scrollHeight - logBoxDisplayElement.clientHeight;

export function logBoxDisplay() {
    let display = "";

    logBox.forEach(element => display += ` ${element} <br>`)
    
    logBoxDisplayElement.innerHTML=display;
}