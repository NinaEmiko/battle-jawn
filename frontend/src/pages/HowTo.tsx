import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/HowTo.css";
import { useState } from "react";
import HowToArena from "../components/HowToComponents/HowToArena";
import HowToPlay from "../components/HowToComponents/HowToPlay";
import HowToBag from "../components/HowToComponents/HowToBag";
import HowToComingSoon from "../components/HowToComponents/HowToComingSoon";
import HowToHeroes from "../components/HowToComponents/HowToHeroes";
import HowToBattleTank from "../components/HowToComponents/HowToBattleTank";
import HowToBattleHealer from "../components/HowToComponents/HowToBattleHealer";
import HowToBattleDPS from "../components/HowToComponents/HowToBattleDPS";
import HowToBattleCaster from "../components/HowToComponents/HowToBattleCaster";

const HowTo = () => {
    const [activeTab, setActiveTab] = useState("Heroes");
    const [activeRole, setActiveRole] = useState("Tank");
    const [activePlayPage, setActivePlayPage] = useState("Arena");
    const tabs = ["Heroes", "Battle", "Play", "Coming Soon"];
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
            const currentIndex = roles.indexOf(activeRole);
            const newIndex = (currentIndex - 1 + roles.length) % roles.length;
            setActiveRole(roles[newIndex]);
        }
        if (activeTab === tabs[2]){
            const currentIndex = playPages.indexOf(activePlayPage);
            const newIndex = (currentIndex - 1 + playPages.length) % playPages.length;
            setActivePlayPage(playPages[newIndex]);
        }
    };

    const handleDownClick = () => {
        if (activeTab === tabs[1]){
            const currentIndex = roles.indexOf(activeRole);
            const newIndex = (currentIndex + 1 + roles.length) % roles.length;
            setActiveRole(roles[newIndex]);
        }
        if (activeTab === tabs[2]){
            const currentIndex = playPages.indexOf(activePlayPage);
            const newIndex = (currentIndex + 1 + playPages.length) % playPages.length;
            setActivePlayPage(playPages[newIndex]);
        }
    };

    const handleClickSpecificTab = (tab: string) => {
        setActiveTab(tab);
    }

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
    };

    return (        
        <Container>
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Sign Out</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">How To</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
                    <div className="parent-jawn">
                        <div className="display-jawn-tabs-4">
                            <button className={activeTab === tabs[0] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[0])}>{tabs[0]}</button>
                            <button className={activeTab === tabs[1] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[1])}>{tabs[1]}</button>
                            <button className={activeTab === tabs[2] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[2])}>{tabs[2]}</button>
                            <button className={activeTab === tabs[3] ? 'active' : ''} onClick={()=> handleClickSpecificTab(tabs[3])}>{tabs[3]}</button>
                        </div>

                            {activeTab === tabs[0] &&
                                <HowToHeroes />
                            }

                            {activeTab === tabs[1] &&
                                <div className="battle">

                                    <p className="battle-role">{activeRole}</p>

                                    {activeRole === roles[0] &&
                                        <HowToBattleTank />
                                    }

                                    {activeRole === roles[1] &&
                                        <HowToBattleHealer />
                                    }

                                    {activeRole === roles[2] &&
                                        <HowToBattleDPS />
                                    }   

                                    {activeRole === roles[3] &&
                                        <HowToBattleCaster />
                                    }   

                                    <p className="battle-howto">Press up or down to scroll through classes.</p>
                                </div>
                            }

                            {activeTab === tabs[2] &&
                                <div className="battle">

                                    {activePlayPage === playPages[0] &&
                                        <HowToArena />
                                    }

                                    {activePlayPage === playPages[1] &&
                                        <HowToPlay />
                                    }
                                    {activePlayPage === playPages[2] &&
                                        <HowToBag />
                                    }

                                    <p className="battle-howto">Press up or down to scroll through available content.</p>

                                </div>
                            }

                            {activeTab === tabs[3] &&
                                 <HowToComingSoon />
                            }
                    </div>
            </Display>
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={() => handleNavigation("/")}>Exit</button>                    
                    </div>
                    <div className="controls-right">
                        <button className="controls-btn" onClick={()=> handleUpClick()}>Up</button>
                        <button className="controls-btn" onClick={()=> handleTabLeftClick()}>Left</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabRightClick()}>Right</button>
                        <button className="controls-btn" onClick={()=> handleDownClick()}>Down</button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}

export default HowTo;