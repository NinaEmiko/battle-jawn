import "../../styling/TalentTree.css";

const TalentButtonInactive = ({props}:{props:any}) => {

    return (
        <>
            {props.bottom ?
                <button className="talent-jawn-inactive center-jawn">
                    {props.text}
                </button>
                :
                <button className="talent-jawn-inactive">
                    {props.text}
                </button>
            }
        </>
    )
}
export default TalentButtonInactive;