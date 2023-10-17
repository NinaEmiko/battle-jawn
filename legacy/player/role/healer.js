import { Player } from "../player.js";

export class Healer extends Player {
    constructor(role, health, maxHealth, strength, potions, maxPotions, statusAilments){
        super(role, health, maxHealth, strength, potions, maxPotions, statusAilments);
    }
}