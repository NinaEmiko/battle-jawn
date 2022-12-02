import { user } from '../user/user.js';
import { createEnemy } from '../enemy/createEnemy.js';
import { logBox } from '../UI/logBox/logBox.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../UI/UI.js';
import { enemyAttack } from '../enemy/enemyAttack.js';

export const playerAttack = () => {
    let damage = Math.floor(Math.random() * user.strength);
    createEnemy.health = createEnemy.health - damage;
    logBox.push(`You attacked the enemy. You did ${damage} damage.`);
    userInterface();
    logBoxDisplay();
    enemyAttack();
};