import { createEnemy } from "../enemy/createEnemy.js";
import { logBox } from "../UI/logBox/logBox.js";
import { logBoxDisplay } from "../UI/logBox/logBoxDisplay.js";
import { userInterface } from "../UI/UI.js";


export function enemyHeal() {
    createEnemy.health = createEnemy.health + 30;
    createEnemy.potions--;
    logBox.push(`${createEnemy.name} used a potion!`);
    setTimeout(userInterface, 1000);
    setTimeout(logBoxDisplay, 1000);
}