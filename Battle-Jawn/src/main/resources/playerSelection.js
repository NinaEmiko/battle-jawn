import { disableButtons } from "UI/disableButtons.js";

export function playerSelection(input) {
    let createUser;

    if (input === 'button1') {
        const tankData = {
            health: 120,
            maxHealth: 120,
            strength: 15,
            potions: 3,
            maxPotions: 3,
            statusAilments: {
                paralyze: false,
                slow: false,
                vulnerable: false,
                bubble: false
            }
          };
        
        fetch('../Controllers/PlayerController', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(tankData)
          })
          .then(response => {
            if (response.ok) {
              // Tank created successfully
            } else {
              // Failed to create Tank
            }
          })
          .catch(error => {
            // Handle error
          });
        disableButtons();
        window.location.href = "../index.html";
    } else if (input === 'button2') {
        fetch('../Player/Healer', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(tankData)
          })
          .then(response => {
            if (response.ok) {
              // Healer created successfully
            } else {
              // Failed to create Healer
            }
          })
          .catch(error => {
            // Handle error
          });
        disableButtons();
        window.location.href = "../index.html";
    } else if (input === 'button3') {
        localStorage.setItem('User Role', 'DPS')
        disableButtons();
        window.location.href = "../index.html";
    } else if (input === 'button4') {
        localStorage.setItem('User Role', 'Caster')
        disableButtons();
        window.location.href = "../index.html";
    }
    return createUser;
}