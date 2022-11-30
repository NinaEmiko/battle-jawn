import { user } from '../user/user.js';

export const isAlive = () => {
    if (user.health > 0) {
        return true;
    } else {
        alert(`You have been slain!`)
        return false;
    }
}