import "../styling/HeroMove.css";
import "../styling/Container.css";

const HeroMove = ({ roleProp, buttonDisabledProp, handleClickBattleProp }: { roleProp: string; buttonDisabledProp: boolean; handleClickBattleProp: (message: string) => void  })  => {

    return (
        <>
            <div className="battle-btn-display">
                <div className="user-prompt-wrapper-battle">
                    <div className="userPrompt-battle">
                        {"What would you like to do?"}
                    </div>
                    {roleProp == "Tank" &&
                    <div className="hero-move-btns" id="option-buttons">
                        <button onClick={(e) => handleClickBattleProp('Strike')} disabled={buttonDisabledProp} className="hero-move-btn" id="atk-btn">
                            Strike
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Impale')} disabled={buttonDisabledProp} className="hero-move-btn" id="strong-atk-btn">
                            Impale
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Block')} disabled={buttonDisabledProp} className="hero-move-btn" id="special-btn">
                            Block
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
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Heal')} disabled={buttonDisabledProp} className="hero-move-btn" id="special-btn">
                            Heal
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
  