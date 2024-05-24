import "../styling/HeroMove.css";
import "../styling/Container.css";
import { determineResourceIcon } from "../helpers/icon_helper";
import power from "../assets/power.png";
import spirit from "../assets/spirit.png";
import magic from "../assets/magic.png";
import energy from "../assets/energy.png";

const HeroMove = ({ roleProp, buttonDisabledProp, handleClickBattleProp }: { roleProp: string; buttonDisabledProp: boolean; handleClickBattleProp: (message: string) => void  })  => {

    return (
        <>
            <div className="battle-btn-display">
                <div className="user-prompt-wrapper-battle">
                    <div className="userPrompt-battle">
                    </div>
                    {roleProp == "Tank" &&
                    <div className="hero-move-btns" id="option-buttons">
                        <button onClick={(e) => handleClickBattleProp('Strike')} disabled={buttonDisabledProp} className="hero-move-btn" id="atk-btn">
                            Strike
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Impale')} disabled={buttonDisabledProp} className="hero-move-btn" id="strong-atk-btn">
                            Impale
                            {/* <div className="resource-ref-div">
                                <img className="resource-ref" src={power}/>
                                <img className="resource-ref" src={power}/>
                                <img className="resource-ref" src={power}/>
                            </div> */}
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Block')} disabled={buttonDisabledProp} className="hero-move-btn" id="special-btn">
                            Block
                            <div className="resource-ref-div">
                                <img className="resource-ref" src={power}/>
                            </div>
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Potion')} disabled={buttonDisabledProp} className="hero-move-btn" id="ptn-btn">
                            Potion
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Water')} disabled={buttonDisabledProp} className="hero-move-btn" id="wtr-btn">
                            Water
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="hero-move-btn" id="run-btn">
                            Run
                        </button>
                        </div>
                    }
                    {roleProp == "Healer" &&
                    <div className="hero-move-btns" id="option-buttons">
                        <button onClick={(e) => handleClickBattleProp('Wand')} disabled={buttonDisabledProp} className="hero-move-btn" id="atk-btn">
                            Wand
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Holy')} disabled={buttonDisabledProp} className="hero-move-btn" id="strong-atk-btn">
                            Holy
                            <div className="resource-ref-div">
                                <img className="resource-ref" src={spirit}/>
                                <img className="resource-ref" src={spirit}/>
                            </div>
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Heal')} disabled={buttonDisabledProp} className="hero-move-btn" id="special-btn">
                            Heal
                            <div className="resource-ref-div">
                                <img className="resource-ref" src={spirit}/>
                            </div>
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Potion')} disabled={buttonDisabledProp} className="hero-move-btn" id="ptn-btn">
                            Potion
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Water')} disabled={buttonDisabledProp} className="hero-move-btn" id="wtr-btn">
                            Water
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="hero-move-btn" id="run-btn">
                            Run
                        </button>
                        </div>
                    }
                    {roleProp == "Caster" &&
                        <div className="hero-move-btns" id="option-buttons">
                            <button onClick={(e) => handleClickBattleProp('Wand')} disabled={buttonDisabledProp} className="hero-move-btn" id="atk-btn">
                            Wand
                            </button>
                            <button onClick={(e) => handleClickBattleProp('FireBlast')} disabled={buttonDisabledProp} className="hero-move-btn" id="strong-atk-btn">
                            FireBlast
                            </button>
                            <button onClick={(e) => handleClickBattleProp('IceBlast')} disabled={buttonDisabledProp} className="hero-move-btn" id="special-btn">
                            IceBlast
                            {/* <div className="resource-ref-div">
                                <img className="resource-ref" src={magic}/>
                            </div> */}
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Potion')} disabled={buttonDisabledProp} className="hero-move-btn" id="ptn-btn">
                            Potion
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Water')} disabled={buttonDisabledProp} className="hero-move-btn" id="wtr-btn">
                            Water
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="hero-move-btn" id="run-btn">
                            Run
                            </button>
                        </div>
                    }
                    {roleProp == "DPS" &&
                        <div className="hero-move-btns" id="option-buttons">
                            <button onClick={(e) => handleClickBattleProp('Stab')} disabled={buttonDisabledProp} className="hero-move-btn" id="atk-btn">
                            Stab
                            </button>
                            <button onClick={(e) => handleClickBattleProp('BackStab')} disabled={buttonDisabledProp} className="hero-move-btn" id="strong-atk-btn">
                            BackStab
                            {/* <div className="resource-ref-div">
                                <img className="resource-ref" src={energy}/>
                                <img className="resource-ref" src={energy}/>
                                <img className="resource-ref" src={energy}/>
                            </div> */}
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Steal')} disabled={buttonDisabledProp} className="hero-move-btn" id="special-btn">
                            Steal
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Potion')} disabled={buttonDisabledProp} className="hero-move-btn" id="ptn-btn">
                            Potion
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Water')} disabled={buttonDisabledProp} className="hero-move-btn" id="wtr-btn">
                            Water
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="hero-move-btn" id="run-btn">
                            Run
                            </button>
                        </div>
                    }
                </div>
            </div>
        </>
    );
  };
  
  export default HeroMove;
  