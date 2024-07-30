import { NavDropdown } from "react-bootstrap";
import "../styling/NavBar.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const NavJawn = ({props}:{props:any}) => {
    const navigate = useNavigate();
  
    const handleNavigation = (path: string) => {
        props.toggleNav();
      navigate(path);
    };

    const handleLogout = () => {
        props.logout();
      };    
    
    return (
        <div
        className="dropdown-menu">
            <div className="dropdown-item" onClick={() => handleNavigation('/')}>
                Home
            </div>
            <div className="dropdown-item" onClick={() => handleNavigation('/leader-board')}>
                Leader Board
            </div>
            <div className="dropdown-item" onClick={() => handleNavigation('/how-to')}>
                How To
            </div>
            <div className="dropdown-item" onClick={() => handleNavigation('/about-us')}>
                About Us
            </div>
            <div className="dropdown-item" onClick={() => handleNavigation('/account-settings')}>
                Account Settings
            </div>
            <hr className="dropdown-divider" />
            <div className="dropdown-item" onClick={() => handleLogout()}>
                Log Out
            </div>
        </div>
    )
}
export default NavJawn;