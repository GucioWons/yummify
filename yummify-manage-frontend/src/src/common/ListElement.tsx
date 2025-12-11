import "./ListElement.css";
import React from "react";

export interface ListElementProps {
    children: React.ReactNode;
}

function ListElement(props: ListElementProps) {
    return (
        <div className="list-element">
            {props.children}
        </div>
    );
}

export default ListElement;