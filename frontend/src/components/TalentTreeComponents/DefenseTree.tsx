import "../../styling/TalentTree.css";

const DefenseTree = () => {

    return (
        <div className="talent-group-jawn">
            <label className="label-jawn">
            <input className="talent-jawn" type="checkbox" /> Improved Health 1
            </label>
            <label className="label-jawn">
            <input className="talent-jawn" type="checkbox" /> Improved Block 1
            </label>
            <label className="label-jawn">
            <input className="talent-jawn" type="checkbox" /> Improved Health 2
            </label>
            <label className="label-jawn">
            <input className="talent-jawn" type="checkbox" /> Improved Block 2
            </label>
            <label className="label-jawn">
            <input className="talent-jawn" type="checkbox" /> Hydration
            </label>
            <label className="label-jawn">
            <input className="talent-jawn" type="checkbox" /> Final Stand
            </label>
            <label className="label-jawn center-jawn">
            <input className="talent-jawn" type="checkbox" /> Desperation
            </label>
        </div>
    )
}
export default DefenseTree;