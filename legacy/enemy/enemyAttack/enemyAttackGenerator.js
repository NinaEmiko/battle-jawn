import { createEnemy } from "../createEnemy.js";
import { enemySteal } from "../enemySteal.js";
import { enemyImpale } from "./enemyImpale.js";
import { enemyParalyze } from "./enemyParalyze.js";
import { enemyShadowBlast } from "./enemyShadowBlast.js";
import { enemyStab } from "./enemyStab.js";
import { enemyStrike } from "./enemyStrike.js";
import { enemyBite } from "./enemyBite.js"
import{ enemyMaim } from "./enemyMaim.js"
import { enemySoulEater } from "./enemySoulEater.js";
import { user } from "../../user/user.js";

export function enemyAttackGenerator() {
    let generator = Math.floor(Math.random() * 100);

    if (createEnemy.name === "Wolf") {
        generator > 20 ? enemyBite() : enemyMaim();
    } else if (createEnemy.name === "Orc") {
        generator > 25 ? enemyStrike() : enemyImpale();
    } else if (createEnemy.name === "Thief") {
        generator > 5 ? enemyStab() : enemySteal();
    } else {
        if (user.statusAilments.paralyze === false) {
            generator > 15 ? enemyShadowBlast() : enemyParalyze();
        } else { 
            generator > 50 ? enemyShadowBlast() : enemySoulEater();
        }
    }
}