import { useEffect, useState } from "react";
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { Link, useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap.min.css'


interface CustomNavBarProps {
  pageTitle: string;
  onLogout: () => void;
  isLoggedIn: boolean;
}

const CustomNavBar: React.FC<CustomNavBarProps> = ({ pageTitle, onLogout, isLoggedIn }) => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [expanded, setExpanded] = useState(false);
  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    handleSelect();
    navigate(path);
  };

  const handleSelect = () => {
    setExpanded(false);
  };

  const handleToggle = () => {
    if (expanded) {
      setExpanded(false);
    } else {
      setExpanded(true);
    }
  };

  const handleLogout = () => {
    handleSelect();
    onLogout();
  };

  useEffect(() => {
    setLoggedIn(isLoggedIn ? true : false);
  }, [isLoggedIn]);

  return (
    <div>
      <Navbar expand="lg" className="bg-body-tertiary fixed-top" bg="dark" data-bs-theme="dark">
      <Container>
        <Link to="/" className="navbar-brand">
          <Navbar.Brand>{pageTitle}</Navbar.Brand>
        </Link>

          {isLoggedIn &&
          <>
          <NavDropdown onSelect={handleSelect} align="end" title="Menu" id="basic-nav-dropdown">
            <NavDropdown.Item className="drop-down-item" onClick={() => handleNavigation('/')}>
                Home 
              </NavDropdown.Item>
              <NavDropdown.Item className="drop-down-item" onClick={() => handleNavigation('/leader-board')}>
                Leader Board 
              </NavDropdown.Item>
              <NavDropdown.Item className="drop-down-item" onClick={() => handleNavigation('/about-us')}>
                About Us
              </NavDropdown.Item>
              <NavDropdown.Item className="drop-down-item" onClick={() => handleNavigation('/account-settings')}>
                Account Settings 
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item className="drop-down-item" onClick={handleLogout}>
                Log Out
              </NavDropdown.Item>
            </NavDropdown>
        </>
}
      </Container>
    </Navbar>
    </div>
  );
};

export default CustomNavBar;
