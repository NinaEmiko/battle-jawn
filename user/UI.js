import { createEnemy } from '../enemy/createEnemy.js';
import { user } from '../user/user.js';        

const textElement = document.getElementById('text');
const textElement2 = document.getElementById('text2');
const textElement3 = document.getElementById('text3');
const optionsButtonsElement = document.getElementById('option-buttons');  
        
        
export function userInterface() {   
    textElement.innerHTML=
    `Enemy: ${createEnemy.name} || Enemy Health: ${createEnemy.health}`
    textElement2.innerHTML=`Hero: ${user.name} ||  Health: ${user.health} ||  Potions: ${user.potions}`
    textElement3.innerHTML=`What do you wish to do?`
}