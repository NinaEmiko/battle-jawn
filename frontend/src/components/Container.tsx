import { ReactNode } from "react";
import "./styles/Container.css";

const Container: React.FC<{children: ReactNode }> = ( {children}:{children:any} ) => {

    return (
        <div className="container-jawn">
            {children}
      </div>
    )
}
export default Container;