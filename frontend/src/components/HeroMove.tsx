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
                    <div className="btn-grid-roles" id="option-buttons">
                        <button onClick={(e) => handleClickBattleProp('Strike')} disabled={buttonDisabledProp} className="btn-role-battle" id="button1">
                            Strike
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Potion')} disabled={buttonDisabledProp} className="btn-role-battle" id="button2">
                            Potion
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Impale')} disabled={buttonDisabledProp} className="btn-role-battle" id="button3">
                            Impale
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="btn-role-battle" id="button4">
                            Run
                        </button>
                        </div>
                    }
                    {roleProp == "Healer" &&
                    <div className="btn-grid-roles" id="option-buttons">
                        <button onClick={(e) => handleClickBattleProp('Wand')} disabled={buttonDisabledProp} className="btn-role-battle" id="button1">
                            Wand
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Heal')} disabled={buttonDisabledProp} className="btn-role-battle" id="button2">
                            Heal
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Holy')} disabled={buttonDisabledProp} className="btn-role-battle" id="button3">
                            Holy
                        </button>
                        <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="btn-role-battle" id="button4">
                            Run
                        </button>
                        </div>
                    }
                    {roleProp == "Caster" &&
                        <div className="btn-grid-roles" id="option-buttons">
                            <button onClick={(e) => handleClickBattleProp('Wand')} disabled={buttonDisabledProp} className="btn-role-battle" id="button1">
                            Wand
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Potion')} disabled={buttonDisabledProp} className="btn-role-battle" id="button2">
                            Potion
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Blast')} disabled={buttonDisabledProp} className="btn-role-battle" id="button3">
                            Blast
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="btn-role-battle" id="button4">
                            Run
                            </button>
                        </div>
                    }
                    {roleProp == "DPS" &&
                        <div className="btn-grid-roles" id="option-buttons">
                            <button onClick={(e) => handleClickBattleProp('Stab')} disabled={buttonDisabledProp} className="btn-role-battle" id="button1">
                            Stab
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Potion')} disabled={buttonDisabledProp} className="btn-role-battle" id="button2">
                            Potion
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Steal')} disabled={buttonDisabledProp} className="btn-role-battle" id="button3">
                            Steal
                            </button>
                            <button onClick={(e) => handleClickBattleProp('Run')} disabled={buttonDisabledProp} className="btn-role-battle" id="button4">
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
  