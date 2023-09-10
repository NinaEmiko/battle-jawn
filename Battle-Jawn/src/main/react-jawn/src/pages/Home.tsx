import { Link } from "react-router-dom";

function Home() {

    return (
    <div className="container">
          <h1>Welcome Message</h1>
          <Link to="/account">Create an Account</Link>
          <Link to="/hero-creation">Create a Hero</Link>
          <h1>Hero List</h1>
          {/* <Link to="/account">Account Settings</Link> */}           
          <h1>Log Out</h1>
        </div>

)
}
export default Home;