import { Player } from "../player.js";

export class Healer extends Player {
    constructor(name, role, health, maxHealth, strength, potions){
        super(name, role, health, maxHealth, strength, potions);
        this.name = name;
        this.role = role;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.potions = potions;
    }
}