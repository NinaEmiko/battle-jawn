import "../../styling/TalentTree.css";

const ArcaneTree = () => {

    return (
        <div className="talent-group-jawn">
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Improved FireBlast 1
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Resourcefulness 1
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Improved FireBlast 2
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Resourcefulness 2
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Improved IceBlast
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> FrostBite
            </label>
            <label className="label-jawn center-jawn">
                <input className="talent-jawn" type="checkbox" /> Second Nature
            </label>
        </div>
    )
}
export default ArcaneTree;