import { playerMove } from '../player/playerMove/playerMove.js';

let button1 = document.querySelector.bind(document);
let button2 = document.querySelector.bind(document);
let button3 = document.querySelector.bind(document);
let button4 = document.querySelector.bind(document);
let potion1 = document.querySelector.bind(document);
let potion2 = document.querySelector.bind(document);
let potion3 = document.querySelector.bind(document);

export function userInput() {

button1('#button1').addEventListener('click', e => {playerMove("button1")});
button2('#button2').addEventListener('click', e => {playerMove("button2")});
button3('#button3').addEventListener('click', e => {playerMove("button3")});
button4('#button4').addEventListener('click', e => {playerMove("button4")});
potion1('#potion1').addEventListener('click', e => {playerMove("button2")});
potion2('#potion2').addEventListener('click', e => {playerMove("button2")});
potion3('#potion3').addEventListener('click', e => {playerMove("button2")});

}