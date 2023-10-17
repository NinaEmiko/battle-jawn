import { enemyAttack } from './enemyAttack/enemyAttack.js'
import { createEnemy } from './createEnemy.js';
import { enemyHeal } from './enemyHeal.js';

export function enemyMoves() {
    let enemyMove = Math.floor(Math.random() * 100);

    if (enemyMove > 15) {
        enemyAttack();
    } else if (createEnemy.potions < 1) {
        enemyAttack();
    } else {
        enemyHeal();
    }
}