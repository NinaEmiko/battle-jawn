import { Player } from "../player.js";

export class Healer extends Player {
    constructor(name, role, health, maxHealth, strength, potions){
        super(name, role, health, maxHealth, strength, potions);
    }
}