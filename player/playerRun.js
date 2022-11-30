export const playerRun = () => {
    let runRate = Math.floor(Math.random() * 100);

    if (runRate > 75) {
        alert(`You successfully ran away!`);
        window.location.reload();
    } else {
        alert(`You tried to run. It didn't work.`);
    }
}