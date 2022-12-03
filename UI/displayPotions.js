import { user } from "../user/user.js";

const healthPotion1 = document.getElementById("potion1");
const healthPotion2 = document.getElementById("potion2");
const healthPotion3 = document.getElementById("potion3");

function displayPotion1() {
    if (user.potions >= 1) {
        healthPotion1.style.display= "inline";
    } else {
        healthPotion3.style.display= "none";
    }
}

function displayPotion2() {
    if (user.potions >= 2) {
        healthPotion2.style.display= "inline";
    } else {
        healthPotion3.style.display= "none";
    }
}

function displayPotion3() {
    if (user.potions >= 3) {
        healthPotion3.style.display= "inline";
    } else {
        healthPotion3.style.display= "none";
    }
}

export function displayPotion() {
    displayPotion1();
    displayPotion2();
    displayPotion3();
}