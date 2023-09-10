import { Link } from "react-router-dom";
import UserPromptText from "../components/UserPromptText";

function Home() {

    return (

        <div className="container">
            <UserPromptText text={"Battle Jawn"} />
            <Link to="/account-creation">Create an Account</Link>
            <Link to="/hero-creation">Create a Hero</Link>
            <h1>Hero List</h1>
            {/* <Link to="/account">Account Settings</Link> */}           
            <h1>Log Out</h1>
        </div>
    )
}
export default Home;