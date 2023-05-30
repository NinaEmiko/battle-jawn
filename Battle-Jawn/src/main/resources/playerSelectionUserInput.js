import { playerSelection } from 'playerSelection.js';

let button1 = document.querySelector.bind(document);
let button2 = document.querySelector.bind(document);
let button3 = document.querySelector.bind(document);
let button4 = document.querySelector.bind(document);

export function PlayerSelectionUserInput() {

button1('#button1').addEventListener('click', e => {playerSelection("button1")});
button2('#button2').addEventListener('click', e => {playerSelection("button2")});
button3('#button3').addEventListener('click', e => {playerSelection("button3")});
button4('#button4').addEventListener('click', e => {playerSelection("button4")});

}