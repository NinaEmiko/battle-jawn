import { user } from '../../user/user.js';

export function castHeal() {
    if (user.maxHealth - user.health > 30){
        user.health = user.health + 30;
    } else {
        user.health = user.maxHealth;
    }
}