import { Enemy } from '../enemy/enemy.js';
import { newEnemy } from '../enemy/NewEnemy.js';

let health;
let potions;
let strength;

if (newEnemy === "Wolf") {
    health = 50;
    potions = 1;
    strength = 10;
} else if (newEnemy === "Orc") {
    health = 100;
    potions = 2;
    strength = 15;
} else if (newEnemy === 'Spirit') {
    health = 150;
    potions = 2
    strength = 20;
} else {
    health = 90;
    potions = 4;
    strength = 17;
}

export const createEnemy = new Enemy(newEnemy, health, health, potions, strength);
