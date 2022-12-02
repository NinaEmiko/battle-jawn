import { Enemy } from '../enemy/enemy.js';
import { newEnemy } from '../enemy/Newenemy.js';

let health;
let strength;

if (newEnemy === "Wolf") {
    health = 50;
    strength = 10;
} else if (newEnemy === "Orc") {
    health = 100;
    strength = 15;
} else if (newEnemy === 'Spirit') {
    health = 150;
    strength = 20;
} else {
    health = 90;
    strength = 17;
}

export const createEnemy = new Enemy(newEnemy, health, health, strength);
