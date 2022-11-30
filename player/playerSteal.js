import { user } from '../user/user.js';

export const playerSteal = () => {
    let successRate = Math.floor(Math.random() * 100);
    if (successRate > 80){
        user.potions++
        alert(`You stole a potion!`)
    } else {
        alert(`You didn't find anything.`)
    }
};