import { Wolf } from '../enemy/role/wolf.js';
import { Orc } from '../enemy/role/orc.js';
import { Spirit } from '../enemy/role/spirit.js';
import { Thief } from '../enemy/role/thief.js';

const newEnemy = function() {
    let makeEnemy;
    let enemyGenerator = Math.floor(Math.random() * 4);

    if (enemyGenerator === 1) {
        makeEnemy = new Wolf("Wolf", 50, 50, 1, 10);
    } else if (enemyGenerator === 2) {
        makeEnemy = new Orc("Orc", 100, 100, 2, 15);
    } else if (enemyGenerator === 3) {
        makeEnemy = new Spirit("Spirit", 150, 150, 1, 20);
    } else {
        makeEnemy = new Thief("Thief", 90, 90, 4, 17);
    }
    return makeEnemy;
}

export const createEnemy = newEnemy();
