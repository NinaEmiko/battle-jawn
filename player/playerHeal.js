import { user } from '../user/user.js';
import { logBox } from '../logBox/logBox.js';
import { logBoxDisplay } from '../logBox/logBoxDisplay.js';
import { userInterface } from '../user/UI.js';
import { enemyAttack } from '../enemy/enemyAttack.js';

export const playerHeal = () => {

    if (user.potions > 0) {
        if (user.maxHealth - user.health > 30){
            user.health = user.health + 30;
            user.potions--;
        } else {
            user.health = user.maxHealth;
            user.potions--;
        }
        logBox.push(`You consumed a potion. You feel better.`);
        userInterface();
        logBoxDisplay();
        enemyAttack();
    } else {
        logBox.push(`You are out of potions.`);
        userInterface();
        logBoxDisplay();
        enemyAttack();
    }
}