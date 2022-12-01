import { logBox } from '../logBox/logBox.js';
import { user } from '../user/user.js';

export const playerSteal = () => {
    let successRate = Math.floor(Math.random() * 100);
    if (successRate > 80){
        user.potions++
        logBox.push(`You stole a potion!`)
    } else {
        logBox.push(`You didn't find anything.`)
    }
};