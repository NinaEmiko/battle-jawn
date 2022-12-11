import { Player } from "../player.js";

export class Caster extends Player {
    constructor(name, role, health, maxHealth, strength, potions, maxPotions){
        super(name, role, health, maxHealth, strength, potions, maxPotions);
    }
}