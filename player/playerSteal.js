import { logBox } from '../UI/logBox/logBox.js';
import { user } from '../user/user.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../UI/UI.js';
import { enemyAttack } from '../enemy/enemyAttack.js';
import { createEnemy } from '../enemy/createEnemy.js';

export const playerSteal = () => {
    let successRate = Math.floor(Math.random() * 100);
    if (createEnemy.potions > 0) {
        if (successRate > 80){
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
    } else {
        logBox.push(`Enemy is out of potions!`);
            userInterface();
            logBoxDisplay();
            enemyAttack();
    }
};