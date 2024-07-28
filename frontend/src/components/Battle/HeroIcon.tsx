import wizard from "../../assets/icons/wizard.png";
import ninja from "../../assets/icons/ninja.png";
import athena from "../../assets/icons/athena.png";
import antibiotics from "../../assets/icons/antibiotics.png";

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