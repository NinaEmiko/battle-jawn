import healthPotion from "../../../resources/images/healthPotion.png";
import "../styling/BattleContainer.css";

const PotionDisplay = () => {
  const role = window.localStorage.getItem("Role");
  return (
    <>
      {role=="Tank" &&
        <div>
          <img src={healthPotion} className="potions" id="potion1"/>
          <img src={healthPotion} className="potions" id="potion2"/>
          <img src={healthPotion} className="potions" id="potion3"/>
        </div>
      }

      {role=="DPS" &&
        <div>
          <img src={healthPotion} className="potions" id="potion1"/>
          <img src={healthPotion} className="potions" id="potion2"/>
        </div>
      }

      {role=="Caster" &&
        <div>
          <img src={healthPotion} className="potions" id="potion1"/>
          <img src={healthPotion} className="potions" id="potion2"/>
          <img src={healthPotion} className="potions" id="potion3"/>
        </div>
      }
    </>
  );
};

export default PotionDisplay;
