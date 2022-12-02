import { Enemy } from '../../enemy/enemy.js';

export class Orc extends Enemy {
    constructor(name, health, maxHealth, potions, strength) {
        super(name, health, maxHealth, potions, strength);
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.strength = strength;
    }
}