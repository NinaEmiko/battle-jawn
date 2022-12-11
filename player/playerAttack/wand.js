import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";

export function wand() {
    let damage = user.strength * .75;

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        createEnemy.health = createEnemy.health - damage;
        logBox.push(`You attacked the enemy. You did ${damage} damage.`);
    } else {
        logBox.push(`You missed the enemy!`);
    }
    //During real time combat, will have a 1 second cool down
}