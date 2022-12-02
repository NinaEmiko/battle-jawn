import { isAlive } from '../user/isAlive.js';
import { userInterface } from '../UI/UI.js';
import { userInput } from '../user/userInput.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';

function runGame() {
    if (isAlive() === true) {
        userInterface();
        userInput();
        logBoxDisplay();
    }
}

runGame();