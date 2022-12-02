import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";


export function strike() {
    let damage = Math.floor(Math.random() * user.strength);
    createEnemy.health = createEnemy.health - damage;

    if (damage > 0) {
        logBox.push(`You attacked the enemy. You did ${damage} damage.`);
    } else {
        logBox.push(`You missed the enemy!`);
    }
}