import "../styling/HeroIcon.css";
import "../styling/Container.css";
import wizard from "../assets/wizard.png";
import ninja from "../assets/ninja.png";
import athena from "../assets/athena.png";
import antibiotics from "../assets/antibiotics.png";

const HeroIcon = ({heroNameProp}:{heroNameProp: string}) => {

    return (
        <>
        {heroNameProp == "Tank" && 
        <img className="role-icon" src={athena}></img>
        }
        {heroNameProp == "Healer" && 
        <img className="role-icon" src={antibiotics}></img>
        }
        {heroNameProp == "Caster" && 
        <img className="role-icon" src={wizard}></img>
        }
        {heroNameProp == "DPS" && 
        <img className="role-icon" src={ninja}></img>
        }
        </>
    );
  };
  
  export default HeroIcon;