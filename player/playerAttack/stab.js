import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";

export function stab() {
    let damage = Math.floor(Math.random() * user.strength);

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        if (damage < user.strength / 2 && damage !== 0) {
            damage = Math.floor(damage + user.strength / 2);
        }

        let criticalHitChance = Math.floor(Math.random() * 100)
        
            if (criticalHitChance > 90) {
                damage = Math.floor(damage * 1.5);
                logBox.push("Critical Hit!");
            }

        createEnemy.health = createEnemy.health - damage * 1.1;
        logBox.push(`You attacked the enemy. You did ${damage} damage.`);
    } else {
        logBox.push(`You missed the enemy!`);
    }
    //During real time combat, will have a 1 second cool down
}