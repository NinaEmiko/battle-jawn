import { logBox } from '../logBox/logBox.js';
import { user } from '../user/user.js';
import { logBoxDisplay } from '../logBox/logBoxDisplay.js';
import { userInterface } from '../user/UI.js';
import { enemyAttack } from '../enemy/enemyAttack.js';
import { createEnemy } from '../enemy/createEnemy.js';

export const playerSteal = () => {
    let successRate = Math.floor(Math.random() * 100);
    if (successRate > 80 && createEnemy.potions > 0){
        user.potions++;
        createEnemy.potions--;
        logBox.push(`You stole a potion!`);
        userInterface();
        logBoxDisplay();
        enemyAttack();
    } else {
        logBox.push(`You didn't find anything.`);
        userInterface();
        logBoxDisplay();
        enemyAttack();
    }
};