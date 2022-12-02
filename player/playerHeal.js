import { user } from '../user/user.js';
import { logBox } from '../UI/logBox/logBox.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../UI/UI.js';
import { enemyMoves } from '../enemy/enemyMoves.js';
import { castHeal } from '../player/playerHeal/castHeal.js';
import { usePotion } from '../player/playerHeal/usePotion.js';


export const playerHeal = () => {

    if (user.role === "Healer") {
        castHeal();
        logBox.push(`You cast Heal. You feel better.`);
        userInterface();
        logBoxDisplay();
        enemyMoves();
    } else {
        if (user.potions > 0) {
            usePotion();
            logBox.push(`You used a potion! You feel better!`);
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