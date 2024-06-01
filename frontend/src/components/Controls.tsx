import { ReactNode } from "react";
import "./styles/Controls.css";

const Controls: React.FC<{children: ReactNode }> = ( {children}:{children:any} ) => {

    return (
        <div className="controls-jawn">
            {children}
      </div>
    )
}
export default Controls;