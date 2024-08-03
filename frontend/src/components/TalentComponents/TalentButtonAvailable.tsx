import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const TalentButtonAvailable = ({props}:{props:any}) => {

    const handleButtonClick = () => {
        props.handleClickTalent(
            props.text,
            talentDescriptions(props.description),
            "talent-jawn-available")
    }

    return (
        <>
            {props.bottom ?
                <button
                    className="talent-jawn-available center-jawn"
                    onClick={() =>
                        handleButtonClick()}>
                                {props.text}
                </button>
                :
                <button
                    className="talent-jawn-available"
                    onClick={() =>
                        handleButtonClick()}>
                                {props.text}
                </button>
            }
        </>
    )
}
export default TalentButtonAvailable;