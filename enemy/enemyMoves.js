import { enemyAttack } from '../enemy/enemyAttack.js'
import { enemySteal } from '../enemy/enemySteal.js';
import { createEnemy } from '../enemy/createEnemy.js';
import { enemyHeal } from '../enemy/enemyHeal.js';
import { newEnemy } from '../enemy/newEnemy.js';

export function enemyMoves() {
    let enemyMove = Math.floor(Math.random() * 100);

    if (enemyMove > 20) {
        enemyAttack();
    } else if (enemyMove < 20 && createEnemy.name === "Thief") {
        if (createEnemy.potions < 1) {
            enemyAttack();
        } else {
            enemySteal();
        }
    } else if (enemyMove < 20 && createEnemy.name !== Thief) {
        if (createEnemy.potions < 1) {
            enemyAttack();
        } else {
        enemyHeal();
        }
    }
}