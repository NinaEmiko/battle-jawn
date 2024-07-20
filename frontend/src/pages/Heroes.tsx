import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styling/MyHeroes.css";
import PopUp from "../components/PopUp";
import { fetchHeroes, restHero, deleteHero } from "../api/api";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import Cookies from "js-cookie";
import Hero from "../components/Heroes/Hero";
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
    // console.log(data)
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
      props.setIsVisible("Map", id);
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
            <PageName props={"Heroes"} />
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
                            {activeTab === "Hero" && heroList.length < 5 &&
                                <div className="display-jawn-tab">
                                    <button  onClick={() => handleNavigation("/create-hero")}>Create New Hero</button>
                                </div>
                            }

                            {heroList.length > 0 &&
                                <div className="hero-header-jawn">
                                    {activeTab === tabs[0] &&
                                      <Hero props={heroList[currentHeroIndex]} />
                                    }
                                    {activeTab === tabs[1] &&
                                      <TalentTree props={heroList[currentHeroIndex]} />
                                    }
                                </div>
                            }
                        </div>
                    }
                </>
            </Display>
            {activeTab === "Talent Tree" &&
              <Controls
                handleClickLeftBtnBottom={() => handleClickHeroes()}
                leftBtnBottomText="Back"
              />
            }
            
            {activeTab === "Hero" &&
              <Controls
                handleClickLeftBtnTop={() => handleClickPlay(heroList[currentHeroIndex].id)}
                leftBtnTopText="Play"
                handleClickLeftBtnMiddle={() => handleClickTalents()}
                leftBtnMiddleText="Talents"
                handleClickLeftBtnBottom={() => handleClickDelete(heroList[currentHeroIndex].id)}
                leftBtnBottomText="Delete"
                handleClickRightBtnLeft={() => previousHero()}
                rightBtnTopText="Left"
                handleClickRightBtnRight={() => nextHero()}
                rightBtnRightText="Right"
              />
            }
        </Container>
  );
};

export default Heroes;
