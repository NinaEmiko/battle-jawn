import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const TalentButtonActive = ({props}:{props:any}) => {

    const handleButtonClick = () => {
        props.handleClickTalent(
            props.text,
            talentDescriptions(props.description),
            "talent-jawn-active")
    }

    return (
        <>
            {props.bottom ?
                <button
                    className="talent-jawn-active center-jawn"
                    onClick={() =>
                        handleButtonClick()}>
                                {props.text}
                </button>
            :
                <button
                    className="talent-jawn-active"
                    onClick={() =>
                            handleButtonClick()}>
                                {props.text}
                </button>
            }
        </>
    )
}
export default TalentButtonActive;