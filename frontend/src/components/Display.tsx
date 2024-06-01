import { ReactNode } from "react";
import "./styles/Display.css";

const Display: React.FC<{children: ReactNode }> = ( {children}:{children:any} ) => {

    return (
        <div className="display-jawn">
            {children}
      </div>
    )
}
export default Display;