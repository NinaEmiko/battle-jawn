import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/Tabs.css";
import "../styling/Scroll.css";
import { useEffect, useState } from "react";
import HowToArena from "../components/HowToComponents/HowToArena";
import HowToShop from "../components/HowToComponents/HowToShop";
import HowToBag from "../components/HowToComponents/HowToBag";
import HowToComingSoon from "../components/HowToComponents/HowToComingSoon";
import HowToHeroes from "../components/HowToComponents/HowToHeroes";
import HowToBattleTank from "../components/HowToComponents/HowToBattleTank";
import HowToBattleHealer from "../components/HowToComponents/HowToBattleHealer";
import HowToBattleDPS from "../components/HowToComponents/HowToBattleDPS";
import HowToBattleCaster from "../components/HowToComponents/HowToBattleCaster";
import HowToTalents from "../components/HowToComponents/HowToTalents";
import HowToStats from "../components/HowToComponents/HowToStats";
import Scroll from "../components/Scroll";

const HowTo = ({props}:{props:any}) => {
    const [activeTab, setActiveTab] = useState("Heroes");
    const [previousRole, setPreviousRole] = useState(-1);
    const [activeRole, setActiveRole] = useState(0);
    const [nextRole, setNextRole] = useState(1);
    const [previousPlayPage, setPreviousPlayPage] = useState(-1);
    const [activePlayPage, setActivePlayPage] = useState(0);
    const [nextPlayPage, setNextPlayPage] = useState(1);
    const [previousHeroesPage, setPreviousHeroesPage] = useState(-1);
    const [activeHeroesPage, setActiveHeroesPage] = useState(0);
    const [nextHeroesPage, setNextHeroesPage] = useState(1);
    const tabs = ["Heroes", "Battle", "Play", "Upcoming"];
    const roles = ["Tank", "Healer", "DPS", "Caster"];
    const playPages = ["Arena", "Store", "Bag"];
    const heroesPages = ["Heroes", "Talents", "Stats"];
    

    //NAVIGATION
    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
    };

    //HANDLER FUNCTIONS
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

    const handleUpClick = () => {
        if (activeTab === tabs[1]){
            setActiveRole((activeRole - 1 + roles.length) % roles.length);
        }
        if (activeTab === tabs[2]){
            setActivePlayPage((activePlayPage - 1 + playPages.length) % playPages.length);
        }
        if (activeTab === tabs[0]){
            setActiveHeroesPage((activeHeroesPage - 1 + heroesPages.length) % heroesPages.length);
        }
    };

    const handleDownClick = () => {
        if (activeTab === tabs[1]){
            setActiveRole((activeRole + 1 + roles.length) % roles.length);
        }
        if (activeTab === tabs[2]){
            setActivePlayPage((activePlayPage + 1 + playPages.length) % playPages.length);
        }
        if (activeTab === tabs[0]){
            setActiveHeroesPage((activeHeroesPage + 1 + heroesPages.length) % heroesPages.length);
        }
    };

    const handleClickSpecificTab = (tab: string) => {
        setActiveTab(tab);
    }

    //USE EFFECTS
    useEffect(() => {
        setPreviousPlayPage((activePlayPage - 1 + playPages.length) % playPages.length);
        setNextPlayPage((activePlayPage + 1) % playPages.length);
    }, [activePlayPage, playPages.length]);

      useEffect(() => {
        setPreviousRole((activeRole - 1 + roles.length) % roles.length);
        setNextRole((activeRole + 1) % roles.length);
    }, [activeRole, roles.length]);

      useEffect(() => {    
        setPreviousHeroesPage((activeHeroesPage - 1 + heroesPages.length) % heroesPages.length);
        setNextHeroesPage((activeHeroesPage + 1) % heroesPages.length);
    }, [activeHeroesPage, heroesPages.length]);

    return (        
        <Container>
            <PageName props={{title: "How To", currentUser: props.currentUser, toggleNav:props.toggleNav}} />
            <Display>
                    <div className="parent-jawn">
                        <div className="display-tabs-top-4">
                            <button className={activeTab === tabs[0] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[0])}>{tabs[0]}</button>
                            <button className={activeTab === tabs[1] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[1])}>{tabs[1]}</button>
                            <button className={activeTab === tabs[2] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[2])}>{tabs[2]}</button>
                            <button className={activeTab === tabs[3] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[3])}>{tabs[3]}</button>
                        </div>

                            {activeTab === tabs[0] &&
                                <>
                                    {activeHeroesPage === 0 &&
                                        <HowToHeroes />
                                    }

                                    {activeHeroesPage === 1 &&
                                        <HowToTalents />
                                    }

                                    {activeHeroesPage === 2 &&
                                        <HowToStats />
                                    }

                                    <Scroll props={{
                                        prev: heroesPages[previousHeroesPage],
                                        active: heroesPages[activeHeroesPage],
                                        next: heroesPages[nextHeroesPage]
                                    }} />
                                </>
                            }

                            {activeTab === tabs[1] &&
                                <>
                                    {activeRole === 0 &&
                                        <HowToBattleTank />
                                    }

                                    {activeRole === 1 &&
                                        <HowToBattleHealer />
                                    }

                                    {activeRole === 2 &&
                                        <HowToBattleDPS />
                                    }   

                                    {activeRole === 3 &&
                                        <HowToBattleCaster />
                                    }   

                                    <Scroll props={{
                                        prev: roles[previousRole],
                                        active: roles[activeRole],
                                        next: roles[nextRole]
                                    }} />
                                </>
                            }

                            {activeTab === tabs[2] &&
                                <>
                                    {activePlayPage === 0 &&
                                        <HowToArena />
                                    }

                                    {activePlayPage === 1 &&
                                        <HowToShop />
                                    }

                                    {activePlayPage === 2 &&
                                        <HowToBag />
                                    }

                                    <Scroll props={{
                                        prev: playPages[previousPlayPage],
                                        active: playPages[activePlayPage],
                                        next: playPages[nextPlayPage]
                                    }} />
                                </>
                            }

                            {activeTab === tabs[3] &&
                                 <HowToComingSoon />
                            }
                    </div>
            </Display>
            <Controls
                handleClickLeftBtnBottom={() => handleNavigation("/")}
                leftBtnBottomText="Exit"
                handleClickRightBtnTop={() => handleUpClick()}
                rightBtnTopText="ᐃ"
                handleClickRightBtnLeft={() => handleTabLeftClick()}
                rightBtnLeftText="ᐊ"
                handleClickRightBtnRight={() => handleTabRightClick()}
                rightBtnRightText="ᐅ"
                handleClickRightBtnBottom={() => handleDownClick()}
                rightBtnBottomText="ᐁ"
            />
        </Container>
    )
}

export default HowTo;