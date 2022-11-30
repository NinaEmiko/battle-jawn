import { user } from '../user/user.js';
import { createEnemy } from '../enemy/createEnemy.js';

export const playerAttack = () => {
    let damage = Math.floor(Math.random() * user.strength);
    createEnemy.health = createEnemy.health - damage;
    alert(`You attacked the enemy. You did ${damage} damage.`)
};