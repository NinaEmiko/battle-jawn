import { Player } from '../player/player.js'

let health;
let strength;
let healthRandomizer = Math.floor(Math.random() * 3)

if (healthRandomizer === 1) {
    health = 100;
    strength = 17;
} else if (healthRandomizer === 2) {
    health = 110;
    strength = 15;
} else {
    health = 90;
    strength = 20;
}

export const user = new Player("Nina", "Warrior", health, health, strength, 2);