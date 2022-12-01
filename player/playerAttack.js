import { user } from '../user/user.js';
import { createEnemy } from '../enemy/createEnemy.js';
import { logBox } from '../logBox/logBox.js';

export const playerAttack = () => {
    let damage = Math.floor(Math.random() * user.strength);
    createEnemy.health = createEnemy.health - damage;
    logBox.push(`You attacked the enemy. You did ${damage} damage.`)
};