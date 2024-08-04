import { activateTalent } from "../../api/api";
import Container from "../Container";
import Controls from "../Controls";
import Display from "../Display";
import PageName from "../PageName";

const TalentPopUp = ({props}:{props:any}) => {

    const handleClickConfirm = async () => {
        const data = await activateTalent(props.heroId, props.talent)
        props.onClickConfirm();
    }

    return ( 
        <Container>
            <PageName props={{title: props.talent, currentUser: props.currentUser, toggleNav:props.toggleNav}} />
            <Display>
            {props.type === "reset" ?
                <div className="parent-jawn">
                    <div className="child-jawn">
                        <p className="text-jawn">Talents reset successfully.</p>
                    </div>
                </div>
                :
                <div className="parent-jawn">
                    <div className="child-jawn">
                        <p className="text-jawn">{props.description}</p>
                    </div>
                </div>
            }
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
  