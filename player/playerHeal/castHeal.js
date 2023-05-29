import { user } from '../../user/user.js';

export function castHeal() {
    if (user.maxHealth - user.health > 30){
        user.health = user.health + 30;
        user.mana = user.mana - 30;
    } else {
        user.health = user.maxHealth;
        user.mana = user.mana - 30;
    }
    //During real time combat, will have a 3.5 second cool down
}