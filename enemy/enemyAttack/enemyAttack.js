import { createEnemy } from '../../enemy/createEnemy.js';
import { logBox } from '../../UI/logBox/logBox.js';
import { userInterface } from '../../UI/UI.js';
import { logBoxDisplay } from '../../UI/logBox/logBoxDisplay.js';
import { isAlive } from '../../user/isAlive.js';
import { enemyAttackGenerator } from '../../enemy/enemyAttack/enemyAttackGenerator.js';
import { deathDisableButtons } from '../../UI/deathDisableButtons.js';

export const enemyAttack = () => {
    if (createEnemy.health > 0) {
        enemyAttackGenerator();
        setTimeout(userInterface, 1000);
        setTimeout(logBoxDisplay, 1000);
        isAlive();
    } else {
        deathDisableButtons();
        logBox.push(`You have vanquished the ${createEnemy.name}!`);
        userInterface();
        logBoxDisplay();
        setTimeout(function() {window.location.reload();
        }, 3000);
    }
}