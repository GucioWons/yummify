import DisplayButtons from "./buttons/DisplayButtons.tsx";
import React, {ReactNode} from "react";
import "./Display.css"

export interface DisplayProps {
    children: React.ReactNode;
    onEditClick?: () => void;
    onCloseClick: () => void;
    additionalButtons?: ReactNode;
}

function Display(props: DisplayProps) {
    const {children, onEditClick, onCloseClick, additionalButtons} = props;

    return (
        <div>
            {children}
            <DisplayButtons onEditClick={onEditClick} onCloseClick={onCloseClick} additionalButtons={additionalButtons} />
        </div>
    );
}

export default Display;