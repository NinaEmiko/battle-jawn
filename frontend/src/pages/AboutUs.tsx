import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/AboutUs.css";
import "../styling/Tabs.css";
import { useState } from "react";

const AboutUs = () => {
    const [activeButton, setActiveButton] = useState("About Us");

    const handleTabClick = (button: string) => {
        setActiveButton(button);
      };

      const navigate = useNavigate();

      const handleNavigation = (path: string) => {
          navigate(path);
      };

    return (        
        <Container>
            <PageName props={"About Us"} />
            <Display>
                {activeButton === "About Us" &&
                    <div className="parent-jawn">
                        <div className="child-jawn">
                            <p className="text-jawn">Battle Jawn is a turn-based rpg-style game. Built with Spring Boot and React and deployed through Railway.app, Battle Jawn began as a way for it's creator to learn how to code in vanilla javascript. It was later migrated to Spring Boot/React in order to learn the tools and languages that she uses in her current role as a software developer in the Fin-Tech industry.</p>
                        </div>
                    </div>
                }

                {activeButton === "Contact" &&
                    <div className="parent-jawn">
                        <div className="child-jawn">
                            <div className="link-jawn">
                                <a href="https://github.com/NinaEmiko/battle-jawn">GitHub</a>
                            </div>
                            <div className="link-jawn">
                                <a href="https://www.linkedin.com/in/nina-mcnair/">LinkedIn</a>
                            </div>
                            <div className="link-jawn">
                                <a href="mailto:TheBattleJawn@gmail.com">Email</a>
                            </div>
                        </div>
                    </div>
                }

                    <div className="display-tabs-top-2">
                        <button className={activeButton === 'About Us' ? 'active' : ''} onClick={()=> handleTabClick("About Us")}>About Us</button>
                        <button className={activeButton === 'Contact' ? 'active' : ''} onClick={()=> handleTabClick("Contact")}>Contact</button>
                    </div>
            </Display>
            <Controls
                handleClickLeftBtnBottom={() => handleNavigation("/")}
                leftBtnBottomText="Exit"
                handleClickRightBtnLeft={() => handleTabClick("About Us")}
                rightBtnLeftText="ᐊ"
                handleClickRightBtnRight={() => handleTabClick("Contact")}
                rightBtnRightText="ᐅ"
                rightBtnTopText="ᐃ"
                rightBtnBottomText="ᐁ"

            />
        </Container>
    )
}

export default AboutUs;