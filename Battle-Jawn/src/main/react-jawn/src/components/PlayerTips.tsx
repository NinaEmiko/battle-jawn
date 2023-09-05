import React from 'react'

let defaultTips = ["Here is one tip.", "Here is another"]

const handleRandomTip = () => {
  return defaultTips[1];
}

const PlayerTips = () => {
  return (
    <div>{handleRandomTip()}</div>
  )
}

export default PlayerTips