import { enemyAttack } from '../enemy/enemyAttack/enemyAttack.js'
import { enemySteal } from '../enemy/enemySteal.js';
import { createEnemy } from '../enemy/createEnemy.js';
import { enemyHeal } from '../enemy/enemyHeal.js';

export function enemyMoves() {
    let enemyMove = Math.floor(Math.random() * 100);

    if (createEnemy.name === "Thief") {
        if (enemyMove > 20) {
            enemyAttack();
        } else if (enemyMove < 20) {
            if (createEnemy.potions < 1) {
                enemyAttack();
            } else {
                enemySteal();
            }
        }
    } else {
        if (enemyMove > 20) {
            enemyAttack();
        } else if (createEnemy.potions < 1) {
            enemyAttack();
        } else {
            enemyHeal();
        }
    }
}