const div = document.getElementById("div");
function colorText() {
    const words = div.textContent.split(" ");
    div.innerHTML = "";
    words.forEach((word => {
        const span = div.appendChild(document.createElement('span'));
        span.textContent = word + " ";
        if (word === "health") span.classList.add('green');
        if (word === "damage") span.classList.add('red');
    }));
};
div.addEventListener("blur", colorText);
colorText();