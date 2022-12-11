import { createEnemy } from '../../enemy/createEnemy.js';
import { user } from  '../../user/user.js';
import { logBox } from '../../UI/logBox/logBox.js';

export function enemyShadowBlast() {
    let damage = Math.floor(Math.random() * createEnemy.strength);

    let missed = false;
    if (damage === 0) {missed = true};

    if (missed === false) {
        
        if (damage < createEnemy.strength / 2 && damage !== 0) {
            damage = Math.floor(damage + createEnemy.strength / 2);
        }

        let criticalHitChance = Math.floor(Math.random() * 100)
        
            if (criticalHitChance > 90) {
                damage = Math.floor(damage * 1.5);
                logBox.push("Critical Hit!");
            }

        user.health = user.health - damage;
        logBox.push(`${createEnemy.name} used SHADOWBLAST! ${createEnemy.name} did ${damage} damage.`); 
    } else {
        logBox.push(`${createEnemy.name}'s SHADOWBLAST missed you!`);
    }
}