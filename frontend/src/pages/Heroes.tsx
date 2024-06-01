import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import classNames from 'classnames';
import "../styling/MyHeroes.css";
import PopUp from "./PopUp";
import { determineMaxExperience, determineNumerator } from "../helpers/experience_helper";
import { fetchHeroes, restHero, deleteHero } from "../api/api";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";

function Heroes( {props}:{props:any} ) {
  const [deleteHeroId, setDeleteHeroId] = useState(0);
  const [heroList, setHeroList] = useState([]);
  const [popUpType, setPopUpType] = useState("");
  const [popUpContent, setPopUpContent] = useState("");
  const [showPopUp, setShowPopUp] = useState(false);
  const [currentHeroIndex, setCurrentHeroIndex] = useState(0);
  const [activeButton, setActiveButton] = useState("Play");

  const handleTabClick = (button: string) => {
      setActiveButton(button);
    };

  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  const fetchHeroesCall = async () => {
    const data = await fetchHeroes(props.accountId);
    setHeroList(data);
  }

  const handleRest = async (id: any) => {
    await restHero(id);
    fetchHeroesCall()
  }

  const handleOkButtonClick = () => {
    setShowPopUp(false);
  }

  const handleConfirmButtonClick = async () => {
    setShowPopUp(false);
    await deleteHero(deleteHeroId);
    fetchHeroesCall();
  }

  const handleClickOKBtn = (id: number) => {
    if (activeButton === "Play") {
      if (heroList[currentHeroIndex].health === 0) {
        setPopUpType("jawn");
        setPopUpContent("This hero has no health. You must use a potion or wait until tomorrow to play again.");
        setShowPopUp(true);
      } else {
        props.setIsVisible("open-map", id);
      }
    } else if (activeButton === "Delete") {
      setPopUpType("confirmation");
      setPopUpContent("delete hero");
      setShowPopUp(true);
      setDeleteHeroId(id);
      setCurrentHeroIndex(0);
    }
  }

  const previousHero = () => {
    if(currentHeroIndex == 0) {
      setCurrentHeroIndex(heroList.length - 1)
    } else {
      setCurrentHeroIndex(currentHeroIndex - 1);
    }
  };

  const nextHero = () => {
    if(currentHeroIndex == heroList.length - 1) {
      setCurrentHeroIndex(0)
    } else {
      setCurrentHeroIndex(currentHeroIndex + 1);
    }
  };
    
  useEffect(() => {
    fetchHeroesCall();
  }, [showPopUp])
  useEffect(() => {
    fetchHeroesCall();
  }, [])

  return (
      <Container>
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Sign Out</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">Heroes</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
                <>
                  {showPopUp &&
                    <PopUp 
                      props={{
                          type: popUpType,
                          content: popUpContent,
                          onClickOk: handleOkButtonClick,
                          onClickConfirm: handleConfirmButtonClick
                      }} 
                    />   
                  }

                  {!showPopUp &&

                  <div >

                    {heroList.length > 0 &&

                    <div className="hero-header-jawn">
                        <div className="hero-name-level">
                        <div className="hero-name"> {heroList[currentHeroIndex].name} </div>
                    </div>
                    <div className="hero-level"> Lvl {heroList[currentHeroIndex].level} {heroList[currentHeroIndex].role} </div>

                    <table className="my-heroes-table">
                    <tbody>
                        <tr>
                        <td className="row-jawn">Health:</td>
                        <td className="data-jawn" id="health-jawn">{heroList[currentHeroIndex].health} / {heroList[currentHeroIndex].maxHealth}</td>
                        </tr>
                        {heroList[currentHeroIndex].role == "Tank" &&
                        <tr>
                            <td className="row-jawn">Power:</td>
                            <td className="data-jawn" id="resource-jawn">{heroList[currentHeroIndex].resource} / {heroList[currentHeroIndex].maxResource}</td>
                        </tr>
                        }
                        {heroList[currentHeroIndex].role == "Healer" &&
                        <tr>
                            <td className="row-jawn">Spirit:</td>
                            <td className="data-jawn" id="resource-jawn">{heroList[currentHeroIndex].resource} / {heroList[currentHeroIndex].maxResource}</td>
                        </tr>
                        }
                        {heroList[currentHeroIndex].role == "Caster" &&
                        <tr>
                            <td className="row-jawn">Magic:</td>
                            <td className="data-jawn" id="resource-jawn">{heroList[currentHeroIndex].resource} / {heroList[currentHeroIndex].maxResource}</td>
                        </tr>
                        }
                        {heroList[currentHeroIndex].role == "DPS" &&
                        <tr>
                            <td className="row-jawn">Energy:</td>
                            <td className="data-jawn" id="resource-jawn">{heroList[currentHeroIndex].resource} / {heroList[currentHeroIndex].maxResource}</td>
                        </tr>
                        }
                        <tr>
                        <td className="row-jawn">Coins:</td>
                        <td className="data-jawn" id="health-jawn">{heroList[currentHeroIndex].coins}</td>
                        </tr>
                        <tr>
                        <td className="row-jawn">Current Streak:</td>
                        <td className="data-jawn">{heroList[currentHeroIndex].winStreak}</td>
                        </tr>
                        <tr>
                        <td className="row-jawn">Wins:</td>
                        <td className="data-jawn">{heroList[currentHeroIndex].winCount}</td>
                        </tr>
                        <tr>
                        <td className="row-jawn">Losses:</td>
                        <td className="data-jawn">{heroList[currentHeroIndex].lossCount}</td>
                        </tr>
                        <tr>
                        <td className="row-jawn">Run Count:</td>
                        <td className="data-jawn">{heroList[currentHeroIndex].runCount}</td>
                        </tr>
                    </tbody>
                    </table>
                    <div className="experience-bar-container">
                    <progress className='experience-bar' value={determineNumerator(heroList[currentHeroIndex].level, heroList[currentHeroIndex].experience)} max={determineMaxExperience(heroList[currentHeroIndex].level)}></progress>
                    <span className="experience-fraction">{determineNumerator(heroList[currentHeroIndex].level, heroList[currentHeroIndex].experience)}/{determineMaxExperience(heroList[currentHeroIndex].level)}</span>
                    </div>

                    <div className="display-jawn-tabs">
                        <button className={activeButton === 'Play' ? 'active' : ''} onClick={()=> handleTabClick("Play")}>Play</button>
                        <button className={activeButton === 'Delete' ? 'active' : ''} onClick={()=> handleTabClick("Delete")}>Delete</button>
                    </div>


                    </div>

                
                    }
                    </div>
                    }
                </>
            </Display>
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn" onClick={() => handleNavigation("/create-hero")}>New Hero</button>
                        <button className="controls-btn" onClick={() => props.setIsVisible("open-inventory", heroList[currentHeroIndex].id)}>Bag</button>
                        <button className="controls-btn" onClick={() => props.setIsVisible("open-store", heroList[currentHeroIndex].id)}>Shop</button>                    </div>
                    <div className="controls-right">
                        <button className="controls-btn" onClick={() => previousHero()}>Up</button>
                        <button className="controls-btn" onClick={() => handleTabClick("Play")}>Left</button>
                        <button className="controls-btn" onClick={() => handleClickOKBtn(heroList[currentHeroIndex].id)}>OK</button>
                        <button className="controls-btn" onClick={() => handleTabClick("Delete")}>Right</button>
                        <button className="controls-btn" onClick={() => nextHero()}>Down</button>
                    </div>
                </>
            </Controls>
        </Container>
  );
};

export default Heroes;
