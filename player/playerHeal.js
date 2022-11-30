import { user } from '../user/user.js';

export const playerHeal = () => {

    if (user.potions > 0) {
        if (user.maxHealth - user.health > 25){
            user.health = user.health + 25;
            user.potions--;
        } else {
            user.health = user.maxHealth;
            user.potions--;
        }
        alert(`You consumed a potion. You feel better.`)
    } else {
        alert(`You are out of potions.`)
    }
}