import React from "react";
import healthPotion from "../../../resources/images/HealthPotion.png";
import "../styling/PotionDisplay.css";

const PotionDisplay = () => {
  return (
    <>
      <img className="potions" id="potion1" src={healthPotion}></img>
      <img className="potions" id="potion2" src={healthPotion}></img>
      <img className="potions" id="potion3" src={healthPotion}></img>
    </>
  );
};

export default PotionDisplay;
