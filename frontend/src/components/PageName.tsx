import "../styling/PageName.css";

const PageName = ({props}:{props:any}) => {
    return (
        <div className="page-name-jawn">
            <div className="page-name-column-2">
                <div className="page-name-txt">{props}</div>
            </div>
        </div>
    )
}
export default PageName;