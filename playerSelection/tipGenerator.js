let tips = ['Tank starts with 3 potions!',
            'Tank can withstand the most damage before dying!',
            "DPS has a special 'BackStab' move that is unlocked after 3 consecutive 'Stabs' successfully land on the enemy!",
            'Healer does not require potions to heal themselves!'
]

export function tipGenerator() {
    let newTip = Math.floor(Math.random() * 4);
    return tips[newTip];
}