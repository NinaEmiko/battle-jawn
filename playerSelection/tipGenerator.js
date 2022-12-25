let tips = ['Tank starts with 3 potions!',
            'Tank can withstand the most damage before dying!',
            "DPS has a special 'BackStab' move that is unlocked after 3 consecutive 'Stabs' are landed on the enemy!",
            'Healer does not require potions to heal themselves!',
            'Spirit enemy is weak to the move Holy!',
            'Be careful! Some powerful moves can also hurt you!'
]

export function tipGenerator() {
    let newTip = Math.floor(Math.random() * tips.length);
    return tips[newTip];
}