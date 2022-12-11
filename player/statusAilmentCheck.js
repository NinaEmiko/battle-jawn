import { logBox } from "../UI/logBox/logBox.js";
import { userInterface } from "../UI/UI.js";
import { logBoxDisplay } from "../UI/logBox/logBoxDisplay.js";
import { enemyMoves } from "../enemy/enemyMoves.js";
import { user } from "../user/user.js";

export function paralyzed() {
        logBox.push("You are paralyzed! You cannot move!")
        userInterface();
        logBoxDisplay();
        enemyMoves();
        user.statusAilments.paralyze = false;
}