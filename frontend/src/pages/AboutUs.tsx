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
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Sign Out</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">About Us</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
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
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={() => handleNavigation("/")}>Exit</button>                    
                    </div>
                    <div className="controls-right">
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabClick("About Us")}>Left</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabClick("Contact")}>Right</button>
                        <button className="controls-btn"></button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}

export default AboutUs;