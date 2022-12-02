import { logBox } from "../UI/logBox/logBox.js";
import { logBoxDisplay } from '../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../UI/UI.js';
import { enemyMoves } from "../enemy/enemyMoves.js";

export const playerRun = () => {
    let runRate = Math.floor(Math.random() * 100);

    if (runRate > 75) {
        logBox.push(`You successfully ran away!`);
        window.location.reload();
    } else {
        logBox.push(`You tried to run. It didn't work.`);
        userInterface();
        logBoxDisplay();
        enemyMoves();
    }
}