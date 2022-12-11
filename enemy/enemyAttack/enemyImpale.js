//10% chance
//damage = createEnemy.strength

import { createEnemy } from '../../enemy/createEnemy.js';
import { user } from  '../../user/user.js';
import { logBox } from '../../UI/logBox/logBox.js';

export function enemyImpale() {
    let damage = createEnemy.strength * 1.2;

        let criticalHitChance = Math.floor(Math.random() * 100)
        
            if (criticalHitChance > 90) {
                damage = Math.floor(damage * 1.5);
                logBox.push("Critical Hit!");
            }

        user.health = user.health - damage;
        logBox.push(`${createEnemy.name} used IMPALE! ${createEnemy.name} did ${damage} damage.`); 
}