import { activateTalent } from "../../api/api";
import "../../styling/TalentPopUp.css";
import Container from "../Container";
import Controls from "../Controls";
import Display from "../Display";
import PageName from "../PageName";

const TalentPopUp = ({props}:{props:any}) => {

    const handleClickConfirm = async () => {
        const data = await activateTalent(props.heroId, props.talent)
        console.log(data);
        props.onClickConfirm();
    }

    return ( 
        <Container>
            <PageName props={props.talent} />
            <Display>
                <div className="icon-wrapper">
                </div>
                <div className="talent-description-wrapper">
                    <p className="talent-description-text">{props.description}</p>
                </div>
            </Display>
            {props.type === "available" ?
                <Controls 
                    handleClickLeftBtnBottom={props.onClickOk}
                    leftBtnBottomText="Back"
                    handleClickLeftBtnTop={() => handleClickConfirm()}
                    leftBtnTopText="Activate"
                    rightBtnTopText="ᐃ"
                    rightBtnLeftText="ᐊ"
                    rightBtnRightText="ᐅ"
                    rightBtnBottomText="ᐁ"
                />
            :
                <Controls 
                    handleClickLeftBtnBottom={props.onClickOk}
                    leftBtnBottomText="Back"
                    rightBtnTopText="ᐃ"
                    rightBtnLeftText="ᐊ"
                    rightBtnRightText="ᐅ"
                    rightBtnBottomText="ᐁ"
                />
            }
        </Container>
    );
};
  
export default TalentPopUp;
  