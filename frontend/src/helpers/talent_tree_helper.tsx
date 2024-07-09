const trees = [
    "Defense",
    "Strength",
    "Protection",
    "Spirituality",
    "Arcane",
    "Mindfulness",
    "Dexterity",
    "Stealth"
];

export const treeOneSetter = (role: string) => {
    switch (role){
        case "Tank":
            return trees[0];
        case "Healer":
            return trees[2];
        case "Caster":
            return trees[4];
        default:
            return trees[6];
    }
}

export const treeTwoSetter = (role: string) => {
    switch (role){
        case "Tank":
            return trees[1];
        case "Healer":
            return trees[3];
        case "Caster":
            return trees[5];
        default:
            return trees[7];
    }
}