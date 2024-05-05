export const determinePrice = (item: string) => {
    switch(item) {
        case "Potion":
            return 1;
        case "Wolf scraps":
            return 1;
        case "Wolf pelt":
            return 2;
        case "Wolf paw":
            return 2;
        case "Water":
            return 3;
        case "Vest":
            return 3;
        case "Pants":
            return 3;
        case "Boots":
            return 3;
        case "Helm":
            return 3;
        case "Mask":
            return 3;
        case "Orc necklace":
            return 4;
        case "Jewels":
            return 4;
        case "Sword":
            return 5;
        case "Dagger":
            return 5;
        case "Spirit trinket":
            return 15;

    }
}