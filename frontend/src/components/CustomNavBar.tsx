import { useEffect, useState } from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { Link, useNavigate } from "react-router-dom";

interface CustomNavBarProps {
  pageTitle: string;
  onLogout: () => void;
  isLoggedIn: boolean;
}

const CustomNavBar: React.FC<CustomNavBarProps> = ({ pageTitle, onLogout, isLoggedIn }) => {
  const [loggedIn, setLoggedIn] = useState(false);
  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  const handleLogout = () => {
    onLogout();
    navigate('/');
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
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto">
            <NavDropdown.Item onClick={() => handleNavigation('/')}>
                Home
              </NavDropdown.Item>
              <NavDropdown.Item onClick={() => handleNavigation('/leader-board')}>
                Leader Board
              </NavDropdown.Item>
              <NavDropdown.Item onClick={() => handleNavigation('/account-settings')}>
                Account Settings
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item onClick={handleLogout}>
                Log Out
              </NavDropdown.Item>
          </Nav>
          
        </Navbar.Collapse>
        </>
}
      </Container>
    </Navbar>
    </div>
  );
};

export default CustomNavBar;
