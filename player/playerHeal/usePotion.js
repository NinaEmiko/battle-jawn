import { user } from "../../user/user.js";
import { displayPotion } from "../../UI/displayPotions.js";

export function usePotion() {
    if (user.maxHealth - user.health > 30){
        user.health = user.health + 30;
        user.potions--;
    } else {
        user.health = user.maxHealth;
        user.potions--;
    }
    //During real time combat, will have a 10 second cool down
}