import { isAlive } from '../user/isAlive.js';
import { userInterface } from './user/UI.js';
import { userInput } from '../user/userInput.js';
import { logBoxDisplay } from './logBox/logBoxDisplay.js';

function runGame() {
    if (isAlive() === true) {

        userInterface();
        userInput();
        logBoxDisplay();
    }
}

runGame();