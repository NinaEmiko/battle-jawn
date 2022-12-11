import { Tank } from '../player/role/tank.js';
import { Healer } from '../player/role/healer.js';
import { DPS } from '../player/role/dps.js';
import { Caster } from '../player/role/caster.js';
import { statusAilments } from '../player/statusAilments.js';

const newUser = function() {
    let createUser;
    let healthRandomizer = Math.floor(Math.random() * 4);

    if (healthRandomizer === 1) {
        createUser = new Tank("Nina", "Tank", 120, 120, 15, 3, 3, statusAilments);
    } else if (healthRandomizer === 2) {
        createUser = new Healer("Nina", "Healer", 100, 100, 17, 0, 0, statusAilments);
    } else if (healthRandomizer === 3) {
        createUser = new Caster("Nina", "Caster", 90, 90, 20, 2, 3, statusAilments);
    } else {
        createUser = new DPS("Nina", "DPS", 90, 90, 20, 2, 3, statusAilments);
    }
    return createUser;
}

export const user = newUser();