import React from 'react'

let defaultTips = [
  'Tank starts with 3 potions!',
            'Tank can withstand the most damage before dying!',
            "DPS has a special 'BackStab' move that is unlocked after 3 consecutive 'Stabs' are landed on the enemy!",
            'Healer does not require potions to heal themselves!',
            'Spirit enemy is weak to the move Holy!',
            'Be careful! Some powerful moves can also hurt you!',
            'If you stagger the enemy, you have a chance to attack again before they do!'
]

const handleGenerateRandomTip = () => {
  return defaultTips[Math.floor(Math.random() * (defaultTips.length - 1))];
}

const PlayerTips = () => {
  return (
    <div>{handleGenerateRandomTip()}</div>
  )
}

export default PlayerTips