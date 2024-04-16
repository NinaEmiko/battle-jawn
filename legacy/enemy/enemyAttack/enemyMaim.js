//damage = createEnemy.strength * 1.2;
//25% chance

import { createEnemy } from '../createEnemy.js';
import { user } from  '../../user/user.js';
import { logBox } from '../../UI/logBox/logBox.js';

export function enemyMaim() {
    let damage = createEnemy.strength * 1.5;

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {

        let criticalHitChance = Math.floor(Math.random() * 100)
        
            if (criticalHitChance > 90) {
                damage = Math.floor(damage * 1.5);
                logBox.push("Critical Hit!");
            }

        user.health = user.health - damage;
        logBox.push(`${createEnemy.name} used MAIM! ${createEnemy.name} did ${damage} damage.`); 
    } else {
        logBox.push(`${createEnemy.name}'s MAIM missed you!`);
    }
}