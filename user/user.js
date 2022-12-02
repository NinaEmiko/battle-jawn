import { Player } from '../player/player.js';

let role;
let health;
let strength;
let potions;
let healthRandomizer = Math.floor(Math.random() * 3)

if (healthRandomizer === 1) {
    role = "Tank"
    health = 110;
    strength = 15;
    potions = 3;
} else if (healthRandomizer === 2) {
    role = "Healer"
    health = 100;
    strength = 17;
    potions = 0;
} else {
    role = "DPS"
    health = 90;
    strength = 20;
    potions = 2;
}

export const user = new Player("Nina", role, health, health, strength, potions);