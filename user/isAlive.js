import { logBox } from '../logBox/logBox.js';
import { user } from '../user/user.js';

export const isAlive = () => {
    if (user.health > 0) {
        return true;
    } else {
        logBox.push('You have been slain!');
        //window.location.reload();
    }
}