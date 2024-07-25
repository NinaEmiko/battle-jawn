import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/AboutUs.css";
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
                    <div className="parent-jawn">
                        <div className="child-jawn">
                            <p className="about-us-txt">Coming Soon</p>
                        </div>
                    </div>

                    <div className="display-jawn-tabs">
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