import { NavDropdown } from "react-bootstrap";
import "../styling/PageName.css";
import "../styling/NavBar.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const PageName = ({props}:{props:any}) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();
  
    const handleNavigation = (path: string) => {
      handleSelect();
      navigate(path);
    };
  
    const handleSelect = () => {
        props.toggleNav()    
    };

    const handleLogout = () => {
        handleSelect();
        props.logout();
      };
    
      useEffect(() => {
        setIsLoggedIn(props.currentUser.loggedIn ? true : false);
      }, [props.currentUser.loggedIn]);
    
    
    return (
        <div className="page-name-jawn">

            <div className="page-name-column-2">
                <div className="page-name-txt">{props.title}</div>
            </div>
            {props.currentUser.loggedIn &&
                    <button className="dropdown-toggle" onClick={handleSelect}>
                        Menu
                    </button>                
                }     
        </div>
    )
}
export default PageName;