import { createEnemy } from '../enemy/createEnemy.js';
import { user } from '../user/user.js';
import { displayPotion } from '../UI/displayPotions.js';

const textElement = document.getElementById('text');
const textElement2 = document.getElementById('text2');
const textElement3 = document.getElementById('text3');
//const optionsButtonsElement = document.getElementById('option-buttons');  
const playerHealthBarElement = document.getElementById('playerHealthBar');
const enemyHealthBarElement = document.getElementById('enemyHealthBar');
        
export function userInterface() {
    displayPotion();
    textElement.innerHTML=
    `Enemy: ${createEnemy.name}`
    enemyHealthBarElement.value = createEnemy.health / createEnemy.maxHealth * 100;
    textElement2.innerHTML=`Hero: ${user.role}`
    playerHealthBarElement.value = user.health / user.maxHealth * 100;
    textElement3.innerHTML=`What do you wish to do?`
}