import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const TalentButtonInactive = ({props}:{props:any}) => {


    return (
        <>
            <button className="talent-jawn-inactive">
                {props.text}
            </button>
        </>
    )
}
export default TalentButtonInactive;