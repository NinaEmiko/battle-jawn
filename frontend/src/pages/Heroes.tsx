import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import classNames from 'classnames';
import "../styling/MyHeroes.css";
import PopUp from "./PopUp";
import { fetchHeroes, restHero, deleteHero } from "../api/api";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import Cookies from "js-cookie";
import Hero from "./Hero";
import TalentTree from "./TalentTree";

function Heroes( {props}:{props:any} ) {
  const [deleteHeroId, setDeleteHeroId] = useState(0);
  const [heroList, setHeroList] = useState([]);
  const [popUpType, setPopUpType] = useState("");
  const [popUpContent, setPopUpContent] = useState("");
  const [showPopUp, setShowPopUp] = useState(false);
  const [currentHeroIndex, setCurrentHeroIndex] = useState(0);
  const [activeTab, setActiveTab] = useState("Hero");
  const tabs = ["Hero", "Talent Tree"]

  const handleTabLeftClick = () => {
    const currentIndex = tabs.indexOf(activeTab);
    const newIndex = (currentIndex - 1 + tabs.length) % tabs.length;
    setActiveTab(tabs[newIndex]);
  };

  const handleTabRightClick = () => {
    const currentIndex = tabs.indexOf(activeTab);
    const newIndex = (currentIndex + 1 + tabs.length) % tabs.length;
    setActiveTab(tabs[newIndex]);
  };

  const handleSpecificTabClick = (tab: string) => {
    setActiveTab(tab);
  }

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

  const handleClickPlay = (id: number) => {
    if (heroList[currentHeroIndex].health === 0) {
      setPopUpType("jawn");
      setPopUpContent("This hero has no health. You must use a potion or wait until tomorrow to play again.");
      setShowPopUp(true);
    } else {
      Cookies.set('activeHero', JSON.stringify(currentHeroIndex))
      props.setIsVisible("open-map", id);
    }
  }

  const handleClickTalents = () => {
    setActiveTab(tabs[1]);
  }
  const handleClickHeroes = () => {
    setActiveTab(tabs[0]);
  }

  const handleClickDelete = (id: number) => {
    setPopUpType("confirmation");
    setPopUpContent("delete hero");
    setShowPopUp(true);
    setDeleteHeroId(id);
    setCurrentHeroIndex(0);
  }

  const previousHero = () => {
    if (activeTab === tabs[0]){
      setCurrentHeroIndex(currentHeroIndex === 0 ? heroList.length - 1 : currentHeroIndex - 1);
    }
  };

  const nextHero = () => {
    if (activeTab === tabs[0]){
      setCurrentHeroIndex(currentHeroIndex === heroList.length - 1 ? 0 : currentHeroIndex + 1);
    }
  };

  const checkActiveHero = () =>{
    const storedActiveHero = Cookies.get('activeHero');
    if (storedActiveHero) {
      setCurrentHeroIndex(Number(JSON.parse(storedActiveHero)));
    }
  }       
    
  useEffect(() => {
    fetchHeroesCall();
  }, [showPopUp])
  useEffect(() => {
    fetchHeroesCall();
    checkActiveHero();
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
                        <div>
                            {heroList.length > 0 &&
                                <div className="hero-header-jawn">
                                    {activeTab === tabs[0] &&
                                      <Hero props={heroList[currentHeroIndex]} />
                                    }
                                    {activeTab === tabs[1] &&
                                      <TalentTree props={heroList[currentHeroIndex]} />
                                    }
                                    {activeTab === "Hero" && heroList.length < 5 &&
                                        <div className="display-jawn-tab">
                                            <button  onClick={() => handleNavigation("/create-hero")}>Create New Hero</button>
                                        </div>
                                    }
                                </div>
                            }
                        </div>
                    }
                </>
            </Display>
            <Controls>
                <>
                {activeTab === "Talent Tree" &&
                <>
                    <div className="controls-left">
                        <button className="controls-btn" ></button>
                        <button className="controls-btn" ></button>
                        <button className="controls-btn" onClick={() => handleClickHeroes()}>Back</button>                    
                    </div>
                    <div className="controls-right">
                        <button className="controls-btn" ></button>
                        <button className="controls-btn" ></button>
                        <button className="controls-btn" >OK</button>
                        <button className="controls-btn" ></button>
                        <button className="controls-btn" ></button>
                    </div>
                    </>
                }

                {activeTab === "Hero" &&
                  <>
                      <div className="controls-left">
                          <button style={{color: "green"}} className="controls-btn" onClick={() => handleClickPlay(heroList[currentHeroIndex].id)}>Play</button>
                          <button className="controls-btn" onClick={() => handleClickTalents()}>Talents</button>                    
                          <button style={{color: "red"}} className="controls-btn" onClick={() => handleClickDelete(heroList[currentHeroIndex].id)}>Delete</button>                    
                      </div>
                      <div className="controls-right">
                        <button className="controls-btn" ></button>
                        <button className="controls-btn" onClick={() => previousHero()}>Left</button>
                        <button className="controls-btn" >OK</button>
                        <button className="controls-btn" onClick={() => nextHero()}>Right</button>
                        <button className="controls-btn" ></button>
                    </div>
                  </>
                }
                </>
            </Controls>
        </Container>
  );
};

export default Heroes;
