import { user } from '../user/user.js'
import { userInterface } from '../UI/UI.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { createEnemy } from '../enemy/createEnemy.js';
import { logBox } from '../UI/logBox/logBox.js';

export const enemySteal = () => {
    let successRate = Math.floor(Math.random() * 100);
    if (user.potions > 0) {
        if (successRate > 80){
            createEnemy.potions++;
            user.potions--;
            logBox.push(`${createEnemy.name} stole a potion!`);
            setTimeout(userInterface, 1000);
            setTimeout(logBoxDisplay, 1000);
        } else {
            logBox.push(`${createEnemy.name} attempted to steal! Attempt was unsuccessful!`);
            setTimeout(userInterface, 1000);
            setTimeout(logBoxDisplay, 1000);
        }
    } else {
        logBox.push(`${createEnemy.name} attempted to steal! Too bad you're a broke bitch.`);
        setTimeout(userInterface, 1000);
        setTimeout(logBoxDisplay, 1000);
    }
};