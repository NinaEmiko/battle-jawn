import { logBox } from "../../UI/logBox/logBox.js";
import { user } from "../../user/user.js";
import { createEnemy } from "../../enemy/createEnemy.js";

export function holy() {
    let damage = (Math.floor(Math.random() * user.strength) + user.strength / 2);

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {

        let criticalHitChance = Math.floor(Math.random() * 100)
        
            if (criticalHitChance > 90) {
                damage = Math.floor(damage * 1.5);
                logBox.push("Critical Hit!");
            }
        
        if (createEnemy.name === "Spirit") {
            createEnemy.health = createEnemy.health - damage * 1.5;
        } else {
            createEnemy.health = createEnemy.health - damage;
        }
        logBox.push(`You used Holy! You did ${damage} damage.`);
    } else {
        logBox.push(`You missed the enemy!`);
    }
    //Next enemy attack will -= 5
    //During real time combat, will have a 3 second cool down
}