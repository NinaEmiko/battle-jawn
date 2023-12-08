import { useEffect, useState } from "react";
import "../App.css";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

interface CustomNavBarProps {
  pageTitle: string;
  onLogout: () => void;
  isLoggedIn: boolean;
}

const CustomNavBar: React.FC<CustomNavBarProps> = ({ pageTitle, onLogout, isLoggedIn }) => {
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    setLoggedIn(isLoggedIn ? true : false);
  }, [isLoggedIn]);

  return (
    <div>
      <Navbar expand="lg" className="bg-body-tertiary fixed-top" bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="/">{pageTitle}</Navbar.Brand>
        <Navbar.Collapse id="basic-navbar-nav">
          {loggedIn &&
          <Nav className="ms-auto">
            <Nav.Link href="/">Home</Nav.Link>
            <Nav.Link href="#my-heroes">My Heroes</Nav.Link>
            <NavDropdown title="Settings" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.2">
                Account Settings
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.3" onClick={onLogout}>
                Log Out
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          }
        </Navbar.Collapse>
      </Container>
    </Navbar>
    </div>
  );
};

export default CustomNavBar;
