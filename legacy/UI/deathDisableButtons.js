let button1 = document.getElementById("button1");
let button2 = document.getElementById("button2");
let button3 = document.getElementById("button3");
let button4 = document.getElementById("button4");

function disableButton1() {
    button1.disabled = true;
  }

function disableButton2() {
    button2.disabled = true;
}

function disableButton3() {
    button3.disabled = true;
}

function disableButton4() {
    button4.disabled = true;
}

export function deathDisableButtons() {
    disableButton1();
    disableButton2();
    disableButton3();
    disableButton4();
}