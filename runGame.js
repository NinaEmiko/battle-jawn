import { isAlive } from '../user/isAlive.js';
import { userInterface } from './user/UI.js';
import { userInput } from '../user/userInput.js';

function runGame() {
    if (isAlive() === true) {

        userInterface();
        userInput();
    }
}

runGame();