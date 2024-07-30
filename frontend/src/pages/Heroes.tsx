import { useEffect, useState } from "react";
import "../styling/MyHeroes.css";
import "../styling/Tabs.css";
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
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
  const [currentHeroIndex, setCurrentHeroIndex] = useState(0);
  const [leftTopButtonText, setLeftTopButtonText] = useState("Play");
  const [leftCenterButtonText, setLeftCenterButtonText] = useState("Talents");
  const [leftBottomButtonText, setLeftBottomButtonText] = useState("Delete");
  const [leftDirectionButtonText, setLeftDirectionButtonText] = useState("ᐊ");
  const [rightDirectionButtonText, setRightDirectionButtonText] = useState("ᐅ");
  const [topDirectionButtonText, setTopDirectionButtonText] = useState("ᐃ")
  const [bottomDirectionButtonText, setBottomDirectionButtonText] = useState("ᐁ")

  //API CALLS
  const fetchHeroesCall = async () => {
    const data = await fetchHeroes(props.currentUser.id);
    setHeroList(data);
  }

  const handleRest = async (id: any) => {
    await restHero(id);
    fetchHeroesCall()
  }

  const handleConfirmButtonClick = async () => {
    if (showDeleteConfirmation) {
      if (heroList.length > 0){
        setCurrentHeroIndex(0);
      }
      setShowDeleteConfirmation(false);
      await deleteHero(deleteHeroId);
      fetchHeroesCall();
      setHeroesButtons();
    }
  }

  //HANDLER FUNCTIONS

  const setHeroesButtons = () => {
    setLeftTopButtonText("Play")
    setLeftCenterButtonText("Talents")
    setLeftBottomButtonText("Delete")
  }
  const setDeleteHeroConfirmationButtons = () => {
    setLeftTopButtonText("Confirm")
    setLeftCenterButtonText("-")
    setLeftBottomButtonText("Decline")
  }
  const handleClickNewHero = (id: number) => {
    props.setIsVisible("New Hero", id);
  }

  const handleClickPlay = () => {
    if (heroList[currentHeroIndex].health === 0){

    } else {
      Cookies.set('activeHero', JSON.stringify(currentHeroIndex))
      props.setIsVisible("Map", heroList[currentHeroIndex].id);
    }
  }

  const handleClickTalents = () => {
    Cookies.set('activeHero', JSON.stringify(currentHeroIndex))
    props.setIsVisible("Talents", heroList[currentHeroIndex].id);
  }

  const handleLeftBottomButtonClick = () => {
    if (showDeleteConfirmation) {
      setShowDeleteConfirmation(false);
      setHeroesButtons();
    } else {
      setShowDeleteConfirmation(true);
      setDeleteHeroId(heroList[currentHeroIndex].id);
      setDeleteHeroConfirmationButtons();
    }
  }

  const previousHero = () => {
    if (!showDeleteConfirmation){
      setCurrentHeroIndex(currentHeroIndex === 0 ? heroList.length - 1 : currentHeroIndex - 1);
    }
  };

  const nextHero = () => {
    if (!showDeleteConfirmation){
      setCurrentHeroIndex(currentHeroIndex === heroList.length - 1 ? 0 : currentHeroIndex + 1);
    }
  };

  const checkActiveHero = () =>{
    const storedActiveHero = Cookies.get('activeHero');
    if (storedActiveHero) {
      setCurrentHeroIndex(Number(JSON.parse(storedActiveHero)));
    }
  }       

  //USE EFFECT
    
  useEffect(() => {
    fetchHeroesCall();
  }, [showDeleteConfirmation])
  useEffect(() => {
    fetchHeroesCall();
    checkActiveHero();
  }, [])

  return (
      <Container>
            <PageName props={{title: "Heroes", currentUser: props.currentUser, toggleNav:props.toggleNav}} />
            <Display>
                <>
                    {showDeleteConfirmation &&
                    <div className="parent-jawn">
                        <div className="child-jawn">
                          <p className="warning-txt">WARNING: This action cannot be undone. Are you sure you wish to delete {heroList[currentHeroIndex].name}?</p>
                        </div>
                      </div>
                    }

                    {!showDeleteConfirmation &&
                        <div>
                            {heroList.length < 5 &&
                                <div className="display-tabs-top-1">
                                    <button className="new-hero-btn" onClick={() => handleClickNewHero(heroList[currentHeroIndex])}>Create New Hero</button>
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
              handleClickLeftBtnTop={() => handleClickPlay()}
              leftBtnTopText={leftTopButtonText}
              handleClickLeftBtnMiddle={() => handleClickTalents()}
              leftBtnMiddleText={leftCenterButtonText}
              handleClickLeftBtnBottom={() => handleLeftBottomButtonClick()}
              leftBtnBottomText={leftBottomButtonText}
              handleClickRightBtnLeft={() => previousHero()}
              rightBtnLeftText={leftDirectionButtonText}
              handleClickRightBtnRight={() => nextHero()}
              rightBtnRightText={rightDirectionButtonText}
              rightBtnTopText={topDirectionButtonText}
              rightBtnBottomText={bottomDirectionButtonText}
            />
        </Container>
  );
};

export default Heroes;
