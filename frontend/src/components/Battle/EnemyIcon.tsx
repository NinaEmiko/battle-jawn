import "../../styling/EnemyIcon.css";
import wolf from "../../assets/wolf.png";
import orc from "../../assets/orc.png";
import ghost from "../../assets/ghost.png";
import ninja from "../../assets/ninja.png";

const EnemyIcon = ({enemyNameProp}:{enemyNameProp: string}) => {

    return (
        <>
        {enemyNameProp == "Wolf" && 
        <img className="role-icon" src={wolf}></img>
        }
        {enemyNameProp == "Orc" && 
        <img className="role-icon" src={orc}></img>
        }
        {enemyNameProp == "Spirit" && 
        <img className="role-icon" src={ghost}></img>
        }
        {enemyNameProp == "Thief" && 
        <img className="role-icon" src={ninja}></img>
        }
        </>
    );
  };
  
  export default EnemyIcon;
  