import { createEnemy } from '../enemy/createEnemy.js';
import { user } from '../user/user.js';

export const enemyAttack = () => {
    if (createEnemy.health > 0) {
        let damage = Math.floor(Math.random() * createEnemy.strength);
        user.health = user.health - damage;
        alert(`${createEnemy.name} has attacked! ${createEnemy.name} did ${damage} damage.`);
    } else {
        alert(`You have vanquished the ${createEnemy.name}!`);
        window.location.reload();
    }
}