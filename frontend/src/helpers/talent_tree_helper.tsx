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

export const talentDescriptions = (talent: string) => {
    switch (talent){

        //ARCANE
        case "Improved FireBlast 1":
            return "Fire Blast does an additional 6 damage per magic.";
        case "Improved FireBlast 2":
            return "Fire Blast does an additional 3 damage if hero has full magic.";
        case "Improved IceBlast":
            return "Ice Blast has the chance to freeze enemy.";
        case "Improved Wand 1 - Arcane":
            return "Increases the damage of Wand by 2.";
        case "Improved Wand 2 - Arcane":
            return "Increases the damage of Wand by 4.";
        case "Improved Wand 3 - Arcane":
            return "On crit, hero gains 2 magic.";

        //MINDFULNESS
        case "Botany 1 - Mindfulness":
            return "Potion grants an additional 5 health.";
        case "Botany 2 - Mindfulness":
            return "Water grants 5 health.";
        case "Botany 3 - Mindfulness":
            return "Potion grants 1 magic.";
        case "Preparation":
            return "Start battle at full magic.";
        case "Resourcefulness 1":
            return "Hero does not need Water in inventory to use Water.";
        case "Resourcefulness 2":
            return "Hero does not need Potion in inventory to use Potion.";
        case "Second Nature":
            return "Ice Blast costs 0 magic."

        //DEXTERITY
        case "Energized":
            return "Start battle at full energy."
        case "Improved BackStab 1":
            return "Increases the damage of BackStab by 3."
        case "Improved BackStab 2":
            return "BackStab has the chance to paralyze an enemy.";
        case "Improved Stab 1":
            return "Increases the damage of Stab by 4.";
        case "Improved Stab 2":
            return "Increases the damage of Stab by 7.";
        case "Improved Stab 3":
            return "Missed Stab consumes 0 energy.";
        case "Peekaboo":
            return "Hero takes 0 damage after a successful BackStab.";

        //STEALTH    
        case "Elation":
            return "Successful steal grants 3 energy.";
        case "First Strike":
            return "Battles begin with a free stab.";
        case "Honor Among Thieves":
            return "Items cannot be stolen from hero.";
        case "Improved Steal 1":
            return "Hero can steal coins.";
        case "Improved Steal 2":
            return "Can steal more coins.";
        case "Organized Mess":
            return "Hero gains 4 additional slots in inventory.";
        case "Sticky Fingaz":
            return "Hero receives store discount.";


        //PROTECTION
        case "Botany 1 - Protection":
            return "Water grants an additional 10 health.";
        case "Botany 2 - Protection":
            return "Potion grants 1 spirit."
        case "Improved Heal 1":
            return "Increases health granted by Heal by 5."
        case "Improved Heal 2":
            return "Increases health granted by Heal by 10."
        case "Improved Heal 3":
            return "Heal has the ability to crit.";
        case "Spiritually Attuned":
            return "Heal costs 0 spirit.";
        case "Survival Instincts":
            return "Successfully running grants full health."

        //SPIRITUALITY
        case "Improved Wand 1 - Spirituality":
            return "Increases damage of Wand by 2."
        case "Improved Wand 2 - Spirituality":
            return "Increases damage of Wand by 4."
        case "Improved Wand 3 - Spirituality":
            return "Wand grants 2 spirit on crit."
        case "Improved Holy 1":
            return "Increases damage of Holy by 5."
        case "Improved Holy 2":
            return "Increases damage of Holy by 7."
        case "Improved Holy 3":
            return "Reduces the cost of Holy by 1 spirit.";
        case "Bubble":
            return "Holy has the chance to protect hero from next enemy attack.";

        //DEFENSE
        case "Desperation":
            return "Successful block, when below 20% health, grants free Strike."
        case "Final Stand":
            return "Heroes last potion heals for an additional 60 health."
        case "Hydration":
            return "Water grants hero 5 health."
        case "Improved Block 1":
            return "Successful blocks grant hero 5 health."
        case "Improved Block 2":
            return "Successful blocks grant hero 10 health."
        case "Improved Health 1":
            return "Increases health by 5."
        case "Improved Health 2":
            return "Increases health by 10."

        //STRENGTH
        case "Titan":
            return "Reduces the cost of Impale to 2 power."
        case "Improved Strike 1":
            return "Increases the damage of Strike by 3."
        case "Improved Strike 2":
            return "Increases the damage of Strike by 5."
        case "Improved Strike 3":
            return "Strike crit grants 2 power."
        case "Improved Impale 1":
            return "Increases the damage of Impale by 2."
        case "Improved Impale 2":
            return "Increases the damage of Impale by 3."
        case "Improved Impale 3":
            return "Impale has the chance to paralyze an enemy."

        //DEFAULT    
        default:
            return "Error loading talent description.";
    }
}