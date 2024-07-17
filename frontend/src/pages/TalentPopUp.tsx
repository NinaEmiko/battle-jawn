import classNames from 'classnames';
import "../styling/TalentPopUp.css";

const TalentPopUp = ({props}:{props:any}) => {

    return (
        <div className="pop-up-jawn">
            {props.type === "available" ?
                <div className="yes-no-btn-jawn">
                    <p className="confirmation-talent">{props.talent}</p>
                    <p className="confirmation-description">{props.description}</p>
                    <div className="confirmation-btns">
                        <button id="no-btn" onClick={props.onClickOk}>Back</button>
                        <button id="yes-btn" onClick={props.onClickConfirm}>Activate</button>
                    </div>
                </div>
            :
                <>
                    <div className="active-jawn">
                    <p className="confirmation-content">{props.talent}</p>
                    <p className="confirmation-content">{props.description}?</p>
                    </div>
                    <div className="ok-btn-jawn">
                        <button className="ok-btn" onClick={props.onClickOk}>Back</button>
                    </div>
                </>
            }
        </div>
    );
  };
  
  export default TalentPopUp;
  