import { isAlive } from '../user/isAlive.js';
import { userInterface } from '../UI/UI.js';
import { userInput } from '../user/userInput.js';
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { buttonDisplay } from '../UI/buttonDisplay.js';

function runGame() {
    if (isAlive() === true) {
        localStorage.clear();
        buttonDisplay();
        userInterface();
        userInput();
        logBoxDisplay();
    }
}

runGame();