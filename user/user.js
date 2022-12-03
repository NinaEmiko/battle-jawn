import { Tank } from '../player/role/tank.js';
import { Healer } from '../player/role/healer.js';
import { DPS } from '../player/role/dps.js';

const newUser = function() {
    let createUser;
    let healthRandomizer = Math.floor(Math.random() * 3);

    if (healthRandomizer === 1) {
        createUser = new Tank("Nina", "Tank", 120, 120, 15, 3, 3);
    } else if (healthRandomizer === 2) {
        createUser = new Healer("Nina", "Healer", 100, 100, 17, 0, 0);
    } else {
        createUser = new DPS("Nina", "DPS", 90, 90, 20, 2, 3);
    }
    return createUser;
}

export const user = newUser();