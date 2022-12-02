let attack = document.getElementById("attack");
let heal = document.getElementById("heal");
let steal = document.getElementById("steal");
let run = document.getElementById("run");

function disableAttackButton() {
    attack.disabled = true;
  }

function disableHealButton() {
    heal.disabled = true;
}

function disableStealButton() {
    steal.disabled = true;
}

function disableRunButton() {
    run.disabled = true;
}

export function deathDisableButtons() {
    disableAttackButton();
    disableHealButton();
    disableStealButton();
    disableRunButton();
}