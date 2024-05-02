import classNames from 'classnames';
import "../styling/PopUp.css";

const PopUp = ({props}:{props:any}) => {

    return (
        <div className="container-jawn-login-form">
            {props.type == "confirmation" ?
            <>
            <h1 className="title-jawn">Please Confirm</h1>
            <p>Are you sure you want to {props.content}?</p>
            <div className="row justify-content-center">
                <button className={classNames('nav-link', 'btn', 'custom-button')} id="ok-btn" onClick={props.onClickOk}>No</button>
                <button className={classNames('nav-link', 'btn', 'custom-button')} id="delete-btn" onClick={props.onClickConfirm}>Yes</button>
            </div>
            </>
            :
            <>
            <p>{props.content}</p>
            <button className="btn" onClick={props.onClickOk}>OK</button>
            </>
            }
        </div>
    );
  };
  
  export default PopUp;
  