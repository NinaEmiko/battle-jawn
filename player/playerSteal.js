import { logBox } from '../UI/logBox/logBox.js';
import { user } from '../user/user.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../UI/UI.js';
import { enemyMoves } from '../enemy/enemyMoves.js';
import { createEnemy } from '../enemy/createEnemy.js';

export const playerSteal = () => {
    let successRate = Math.floor(Math.random() * 100);
    if (user.potions === user.maxPotions) {
        logBox.push(`You cannot carry anymore potions!`);
            userInterface();
            logBoxDisplay();
            enemyMoves();
    } else if (createEnemy.potions > 0) {
        if (successRate > 80){
            user.potions++;
            createEnemy.potions--;
            logBox.push(`You stole a potion!`);
            userInterface();
            logBoxDisplay();
            enemyMoves();
        } else {
            logBox.push(`You didn't find anything.`);
            userInterface();
            logBoxDisplay();
            enemyMoves();
        }
    } else {
        logBox.push(`Enemy is out of potions!`);
            userInterface();
            logBoxDisplay();
            enemyMoves();
    }
    //During real time combat, will have a 15 second cool down
};