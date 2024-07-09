import "../../styling/TalentTree.css";

const ProtectionTree = () => {

    return (
        <div className="talent-group-jawn">
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Improved Heal 1
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Botany 1
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Improved Heal 2
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Botany 2
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Improved Heal 3
            </label>
            <label className="label-jawn">
                <input className="talent-jawn" type="checkbox" /> Bubble
            </label>
            <label className="label-jawn center-jawn">
                <input className="talent-jawn" type="checkbox" /> Survival Instincts
            </label>

        </div>
    )
}
export default ProtectionTree;