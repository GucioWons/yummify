import React from "react";
import "./FieldDisplay.css"

export interface FieldDisplayProps {
    label: string;
    children: React.ReactNode;
}

function FieldDisplay(props: FieldDisplayProps) {
    const {label, children} = props;

    return (
        <div className='field-display'>
            <span className='field-display-label'>{label}</span>
            <div className='field-display-content'>{children}</div>
        </div>
    );
}

export default FieldDisplay;