import { Tank } from '../player/role/tank.js';
import { Healer } from '../player/role/healer.js';
import { DPS } from '../player/role/dps.js';
import { Caster } from '../player/role/caster.js';
import { statusAilments } from '../player/statusAilments.js';

const newUser = function() {
    let createUser;

    if (localStorage.getItem('User Role') === 'Tank') {
        createUser = new Tank("Tank", 120, 120, 15, 3, 3, statusAilments);
    } else if (localStorage.getItem('User Role') === 'Healer') {
        createUser = new Healer("Healer", 100, 100, 17, 0, 0, statusAilments);
    } else if (localStorage.getItem('User Role') === 'Caster') {
        createUser = new Caster("Caster", 90, 90, 20, 2, 3, statusAilments);
    } else if (localStorage.getItem('User Role') === 'DPS'){
        createUser = new DPS("DPS", 90, 90, 20, 2, 3, statusAilments);
    } else {
        window.location.href = "playerSelection/playerSelection.html";
    }
    return createUser;
}

export const user = newUser();