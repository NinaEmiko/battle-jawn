import { Enemy } from '../enemy.js';

export class Spirit extends Enemy {
    constructor(name, health, maxHealth, potions, strength) {
        super(name, health, maxHealth, potions, strength);
    }
}