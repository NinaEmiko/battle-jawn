const Scroll = ({props}:{props:any}) => {
    return (
        <div className="scroll-jawn">
            <div className="previous-scroll">{props.prev} ᐃ</div>
            <div className="active-scroll">{props.active} ●</div>
            <div className="next-scroll">{props.next} ᐁ</div>
        </div>
)
}
export default Scroll;