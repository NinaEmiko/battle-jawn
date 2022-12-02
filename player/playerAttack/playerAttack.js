import { strike } from "../../player/playerAttack/strike.js";
import { logBoxDisplay } from '../../UI/logBox/logBoxDisplay.js';
import { userInterface } from '../../UI/UI.js';
import { enemyMoves } from '../../enemy/enemyMoves.js';

export const playerAttack = () => {
    strike();
    userInterface();
    logBoxDisplay();
    enemyMoves();
};