import { ReactNode } from "react";
import "./styles/PageName.css";

const PageName: React.FC<{children: ReactNode }> = ( {children}:{children:any} ) => {

    return (
        <div className="page-name-jawn">
            {children}
      </div>
    )
}
export default PageName;