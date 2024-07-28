import wolf from "../../assets/icons/wolf.png";
import orc from "../../assets/icons/orc.png";
import ghost from "../../assets/icons/ghost.png";
import ninja from "../../assets/icons/ninja.png";

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
  