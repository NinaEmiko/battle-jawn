import classNames from 'classnames';
import "../styling/PopUp.css";

const PopUp = ({props}:{props:any}) => {

    return (
        <div className="pop-up-jawn">
            {props.type == "confirmation" ?
                <div className="yes-no-btn-jawn">
                    <p className="confirmation-txt">ATTENTION: Please Confirm</p>
                    <p className="confirmation-content">Are you sure you wish to {props.content}?</p>
                    <div className="confirmation-btns">
                        <button id="no-btn" onClick={props.onClickOk}>No</button>
                        <button id="yes-btn" onClick={props.onClickConfirm}>Yes</button>
                    </div>
                </div>
            :
                <>
                    <div className="content-jawn">
                        <p className="content">{props.content}</p>
                    </div>
                    <div className="ok-btn-jawn">
                        <button className="ok-btn" onClick={props.onClickOk}>OK</button>
                    </div>
                </>
            }
        </div>
    );
  };
  
  export default PopUp;
  