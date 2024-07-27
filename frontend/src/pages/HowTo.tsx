import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/HowTo.css";
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

const HowTo = () => {
    const [activeTab, setActiveTab] = useState("Heroes");
    const [previousRole, setPreviousRole] = useState(-1);
    const [activeRole, setActiveRole] = useState(0);
    const [nextRole, setNextRole] = useState(1);
    const [previousPlayPage, setPreviousPlayPage] = useState(-1);
    const [activePlayPage, setActivePlayPage] = useState(0);
    const [nextPlayPage, setNextPlayPage] = useState(1);
    const tabs = ["Heroes", "Battle", "Play", "Upcoming"];
    const roles = ["Tank", "Healer", "DPS", "Caster"];
    const playPages = ["Arena", "Store", "Bag"];

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
            const newIndex = (activeRole - 1 + roles.length) % roles.length;
            setActiveRole(newIndex);
        }
        if (activeTab === tabs[2]){
            const newIndex = (activePlayPage - 1 + playPages.length) % playPages.length;
            setActivePlayPage(newIndex);
        }
    };

    const handleDownClick = () => {
        if (activeTab === tabs[1]){
            const newIndex = (activeRole + 1 + roles.length) % roles.length;
            setActiveRole(newIndex);
        }
        if (activeTab === tabs[2]){
            const newIndex = (activePlayPage + 1 + playPages.length) % playPages.length;
            setActivePlayPage(newIndex);
        }
    };

    const handleClickSpecificTab = (tab: string) => {
        setActiveTab(tab);
    }

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
    };

    useEffect(() => {
        const newPreviousPage = (activePlayPage - 1 + playPages.length) % playPages.length;
        const newNextPage = (activePlayPage + 1) % playPages.length;
    
        setPreviousPlayPage(newPreviousPage);
        setNextPlayPage(newNextPage);
      }, [activePlayPage, playPages.length]);

      useEffect(() => {
        const newPreviousRole = (activeRole - 1 + roles.length) % roles.length;
        const newNextRole = (activeRole + 1) % roles.length;
    
        setPreviousRole(newPreviousRole);
        setNextRole(newNextRole);
      }, [activeRole, roles.length]);

    return (        
        <Container>
            <PageName props={"How To"} />
            <Display>
                    <div className="parent-jawn">
                        <div className="display-tabs-top-4">
                            <button className={activeTab === tabs[0] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[0])}>{tabs[0]}</button>
                            <button className={activeTab === tabs[1] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[1])}>{tabs[1]}</button>
                            <button className={activeTab === tabs[2] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[2])}>{tabs[2]}</button>
                            <button className={activeTab === tabs[3] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[3])}>{tabs[3]}</button>
                        </div>

                            {activeTab === tabs[0] &&
                                <HowToHeroes />
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

                                    <div className="scroll-jawn">
                                        <div className="previous-scroll">{roles[previousRole]} ᐃ</div>
                                        <div className="active-scroll">{roles[activeRole]} ●</div>
                                        <div className="next-scroll">{roles[nextRole]} ᐁ</div>
                                    </div>
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
                                    <div className="scroll-jawn">
                                        <div className="previous-scroll">{playPages[previousPlayPage]} ᐃ</div>
                                        <div className="active-scroll">{playPages[activePlayPage]} ●</div>
                                        <div className="next-scroll">{playPages[nextPlayPage]} ᐁ</div>
                                    </div>
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