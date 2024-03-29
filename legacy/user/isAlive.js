import { logBox } from '../UI/logBox/logBox.js';
import { user } from './user.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../UI/UI.js';
import { deathDisableButtons } from '../UI/deathDisableButtons.js';

export const isAlive = () => {
    if (user.health > 0) {
        return true;
    } else {
        deathDisableButtons();
        logBox.push(`You have been slain!`);
        userInterface();
        logBoxDisplay();
        setTimeout(function() {window.location.href = "playerSelection/playerSelection.html"
        }, 3000);
    }
}