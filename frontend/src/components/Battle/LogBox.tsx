import "../../styling/Container.css";
import "../../styling/BattleContainer.css";

const LogBox = ({battleHistoryProp}:{battleHistoryProp: string[]}) => {

    return (
        <>
            <div className="logBox" id="logBox">
                {battleHistoryProp.slice().reverse().map((item, index) => (
                    <div className="dialog" key={index}>
                        {item}
                        <br />
                    </div>
                ))}
            </div>
        </>
    );
  };
  
  export default LogBox;
  