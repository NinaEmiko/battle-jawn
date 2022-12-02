import { user } from '../user/user.js';
import { createEnemy } from '../enemy/createEnemy.js';
import { logBox } from '../logBox/logBox.js';
import { logBoxDisplay } from '../logBox/logBoxDisplay.js';
import { userInterface } from '../user/UI.js';
import { enemyAttack } from '../enemy/enemyAttack.js';

export const playerAttack = () => {
    let damage = Math.floor(Math.random() * user.strength);
    createEnemy.health = createEnemy.health - damage;
    logBox.push(`You attacked the enemy. You did ${damage} damage.`);
    userInterface();
    logBoxDisplay();
    setTimeout(enemyAttack, 1000);
};