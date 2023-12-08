import "../App.css";

const Header = (props: any) => {

  return (
    <div>
      <header className="app-header">
      <h1 className="app-title">{props.pageTitle}</h1>
      </header>
    </div>
  );
};

export default Header;
