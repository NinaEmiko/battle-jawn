import { user } from "../user/user.js";

let button1 = document.getElementById('button1');
let button2 = document.getElementById('button2');
let button3 = document.getElementById('button3');
let button4 = document.getElementById('button4');
        
export function buttonDisplay() {
    if (user.role === 'Tank') {
        button1.innerHTML= `Strike`
        button2.innerHTML= `Potion`
        button3.innerHTML= `Impale`
        button4.innerHTML= `Run`
    } else if (user.role === 'Healer') {
        button1.innerHTML= `Wand`
        button2.innerHTML= `Cast Heal`
        button3.innerHTML= `Holy`
        button4.innerHTML= `Run`
    } else if (user.role === 'Caster') {
        button1.innerHTML= `Wand`
        button2.innerHTML= `Potion`
        button3.innerHTML= `Blast`
        button4.innerHTML= `Run`
    } else if (user.role === 'DPS') {
        button1.innerHTML= `Stab`
        button2.innerHTML= `Potion`
        button3.innerHTML= `Steal`
        button4.innerHTML= `Run`
    }
}