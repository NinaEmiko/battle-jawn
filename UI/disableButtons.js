let attack = document.getElementById("attack");
let heal = document.getElementById("heal");
let steal = document.getElementById("steal");
let run = document.getElementById("run");

function disableAttackButton() {
    attack.disabled = true;
    setTimeout(()=>{
      attack.disabled = false}, 3000)
  }

function disableHealButton() {
    heal.disabled = true;
    setTimeout(()=>{
        heal.disabled = false}, 3000)
}

function disableStealButton() {
    steal.disabled = true;
    setTimeout(()=>{
      steal.disabled = false}, 3000)
  }

function disableRunButton() {
    run.disabled = true;
    setTimeout(()=>{
        run.disabled = false}, 3000)
}

export function disableButtons() {
    disableAttackButton();
    disableHealButton();
    disableStealButton();
    disableRunButton();
}