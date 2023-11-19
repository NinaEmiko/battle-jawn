import "../styling/LogBoxDisplay.css";

const LogBoxDisplay = (props: any) => {


  return (
    <div className="logBox" id="logBox">{props.props}</div>
  )
}

export default LogBoxDisplay