import { createEnemy } from "../../enemy/createEnemy.js";
import { enemySteal } from "../../enemy/enemySteal.js";
import { enemyImpale } from "../../enemy/enemyAttack/enemyImpale.js";
import { enemyParalyze } from "../../enemy/enemyAttack/enemyParalyze.js";
import { enemyShadowBlast } from "../../enemy/enemyAttack/enemyShadowBlast.js";
import { enemyStab } from "../../enemy/enemyAttack/enemyStab.js";
import { enemyStrike } from "../../enemy/enemyAttack/enemyStrike.js";
import { enemyBite } from "../../enemy/enemyAttack/enemyBite.js"
import{ enemyMaim } from "../../enemy/enemyAttack/enemyMaim.js"

export function enemyAttackGenerator() {
    let generator = Math.floor(Math.random() * 100);

    if (createEnemy.name === "Wolf") {
        generator > 25 ? enemyBite() : enemyMaim();
    } else if (createEnemy.name === "Orc") {
        generator > 25 ? enemyStrike() : enemyImpale();
    } else if (createEnemy.name === "Thief") {
        generator > 25 ? enemyStab() : enemySteal();
    } else {
        generator > 25 ? enemyShadowBlast() : enemyParalyze();
    }
}