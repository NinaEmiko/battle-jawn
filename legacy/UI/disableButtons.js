let button1 = document.getElementById("button1");
let button2 = document.getElementById("button2");
let button3 = document.getElementById("button3");
let button4 = document.getElementById("button4");

function disableButton1() {
    button1.disabled = true;
    setTimeout(()=>{
      button1.disabled = false}, 1000)
  }

function disableButton2() {
    button2.disabled = true;
    setTimeout(()=>{
        button2.disabled = false}, 1000)
}

function disableButton3() {
    button3.disabled = true;
    setTimeout(()=>{
      button3.disabled = false}, 1000)
  }

function disableButton4() {
    button4.disabled = true;
    setTimeout(()=>{
        button4.disabled = false}, 1000)
}

export function disableButtons() {
    disableButton1();
    disableButton2();
    disableButton3();
    disableButton4();
}