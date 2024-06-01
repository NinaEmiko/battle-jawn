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
                <div className="about-us-jawn">
                    <p className="about-us-txt">Coming Soon</p>

                    <div className="display-jawn-tabs">
                        <button className={activeButton === 'About Us' ? 'active' : ''} onClick={()=> handleTabClick("About Us")}>About Us</button>
                        <button className={activeButton === 'Contact' ? 'active' : ''} onClick={()=> handleTabClick("Contact")}>Contact</button>
                    </div>
                </div>
            </Display>
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn">Back</button>                    </div>
                    <div className="controls-right">
                        <button className="controls-btn">Up</button>
                        <button className="controls-btn">Left</button>
                        <button className="controls-btn">OK</button>
                        <button className="controls-btn">Right</button>
                        <button className="controls-btn">Down</button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}

export default AboutUs;