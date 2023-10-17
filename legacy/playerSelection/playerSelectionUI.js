import { PlayerSelectionUserInput } from "./playerSelectionUserInput.js";
import { tipGenerator } from "./tipGenerator.js";

let currentTip = tipGenerator();

let button1 = document.getElementById('button1');
let button2 = document.getElementById('button2');
let button3 = document.getElementById('button3');
let button4 = document.getElementById('button4');
let textElement = document.getElementById('text');
let textElement3 = document.getElementById('text3');
        
button1.innerHTML= `Tank`
button2.innerHTML= `Healer`
button3.innerHTML= `DPS`
button4.innerHTML= `Caster`
textElement.innerHTML=`Tip: ${currentTip}`
textElement3.innerHTML=`Choose a role.`

PlayerSelectionUserInput();