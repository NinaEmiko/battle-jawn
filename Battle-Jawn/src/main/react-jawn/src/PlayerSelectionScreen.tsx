import React from 'react'
import BackgroundImage from "../../resources/images/BattleJawnBackground.png"
import Container from './components/Container';

function PlayerSelectionScreen() {
    return (
        <Container />
    );
  }
  
  document.body.style.backgroundImage = `url("${BackgroundImage}")`;
  
export default PlayerSelectionScreen