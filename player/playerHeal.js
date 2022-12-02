import { user } from '../user/user.js';
import { logBox } from '../UI/logBox/logBox.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../UI/UI.js';
import { enemyMoves } from '../enemy/enemyMoves.js';

export const playerHeal = () => {

    if (user.role === "Healer") {
        if (user.maxHealth - user.health > 30){
            user.health = user.health + 30;
        } else {
            user.health = user.maxHealth;
        }
        logBox.push(`You cast Heal. You feel better.`);
        userInterface();
        logBoxDisplay();
        enemyMoves();
    } else {
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
            enemyMoves();
        } else {
            logBox.push(`You are out of potions!`);
            userInterface();
            logBoxDisplay();
        }
    }
}