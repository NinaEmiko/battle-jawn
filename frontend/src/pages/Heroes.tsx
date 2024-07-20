import { useEffect, useState } from "react";
import "../styling/MyHeroes.css";
import PopUp from "../components/PopUp";
import { fetchHeroes, restHero, deleteHero } from "../api/api";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import Cookies from "js-cookie";
import Hero from "../components/Heroes/Hero";

function Heroes( {props}:{props:any} ) {
  const [deleteHeroId, setDeleteHeroId] = useState(0);
  const [heroList, setHeroList] = useState([]);
  const [popUpType, setPopUpType] = useState("");
  const [popUpContent, setPopUpContent] = useState("");
  const [showPopUp, setShowPopUp] = useState(false);
  const [currentHeroIndex, setCurrentHeroIndex] = useState(0);

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
  const handleClickNewHero = (id: number) => {
    props.setIsVisible("New Hero", id);
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

  const handleClickTalents = (id: number) => {
    Cookies.set('activeHero', JSON.stringify(currentHeroIndex))
    props.setIsVisible("Talents", id);
  }

  const handleClickDelete = (id: number) => {
    setPopUpType("confirmation");
    setPopUpContent("delete hero");
    setShowPopUp(true);
    setDeleteHeroId(id);
    setCurrentHeroIndex(0);
  }

  const previousHero = () => {
    setCurrentHeroIndex(currentHeroIndex === 0 ? heroList.length - 1 : currentHeroIndex - 1);
  };

  const nextHero = () => {
    setCurrentHeroIndex(currentHeroIndex === heroList.length - 1 ? 0 : currentHeroIndex + 1);
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
                            {heroList.length < 5 &&
                                <div className="display-jawn-tab">
                                    <button onClick={() => handleClickNewHero(heroList[currentHeroIndex])}>Create New Hero</button>
                                </div>
                            }

                            {heroList.length > 0 &&
                              <div className="hero-header-jawn">
                                <Hero props={heroList[currentHeroIndex]} />
                              </div>
                            }
                        </div>
                    }
                </>
            </Display>
            <Controls
              handleClickLeftBtnTop={() => handleClickPlay(heroList[currentHeroIndex].id)}
              leftBtnTopText="Play"
              handleClickLeftBtnMiddle={() => handleClickTalents(heroList[currentHeroIndex].id)}
              leftBtnMiddleText="Talents"
              handleClickLeftBtnBottom={() => handleClickDelete(heroList[currentHeroIndex].id)}
              leftBtnBottomText="Delete"
              handleClickRightBtnLeft={() => previousHero()}
              rightBtnLeftText="Left"
              handleClickRightBtnRight={() => nextHero()}
              rightBtnRightText="Right"
            />
        </Container>
  );
};

export default Heroes;
