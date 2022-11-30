import { Player } from '../player/player.js'

let maxHealth;
let health;
let strength;
let healthRandomizer = Math.floor(Math.random() * 3)

if (healthRandomizer === 1) {
    health = 100;
    maxHealth = 100;
    strength = 50;
} else if (healthRandomizer === 2) {
    health = 120;
    maxHealth = 120;
    strength = 40;
} else {
    health = 80;
    maxHealth = 80;
    strength = 65;
}

export const user = new Player("Nina", "Warrior", health, maxHealth, strength, 2);