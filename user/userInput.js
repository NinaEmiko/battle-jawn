import { playerMove } from '../player/playerMove.js';

let attack = document.querySelector.bind(document);
let heal = document.querySelector.bind(document);
let steal = document.querySelector.bind(document);
let run = document.querySelector.bind(document);

export function userInput() {

attack('#attack').addEventListener('click', e => {playerMove("attack")});
heal('#heal').addEventListener('click', e => {playerMove("heal")});
steal('#steal').addEventListener('click', e => {playerMove("steal")});
run('#run').addEventListener('click', e => {playerMove("run")});

}