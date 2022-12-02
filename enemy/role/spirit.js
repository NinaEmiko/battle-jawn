import { Enemy } from '../../enemy/enemy.js';

export class Spirit extends Enemy {
    constructor(name, health, maxHealth, potions, strength) {
        super(name, health, maxHealth, potions, strength);
    }
}