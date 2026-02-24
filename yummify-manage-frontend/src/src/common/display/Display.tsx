import DisplayButtons from "./buttons/DisplayButtons.tsx";
import React from "react";
import "./Display.css"

export interface DisplayProps {
    children: React.ReactNode;
    onEditClick: () => void;
    onCloseClick: () => void;
}

function Display(props: DisplayProps) {
    const {children, onEditClick, onCloseClick} = props;

    return (
        <div>
            {children}
            <DisplayButtons onEditClick={onEditClick} onCloseClick={onCloseClick} />
        </div>
    );
}

export default Display;