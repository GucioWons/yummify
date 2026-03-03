import "./ListElement.css";
import React from "react";

export interface ListElementProps<T> {
    item: T;
    onClick?: (item: T) => void;
    render: (item: T) => React.ReactNode;
}

function ListElement<T>(props: ListElementProps<T>) {
    const { item, onClick, render } = props;

    return (
        <div
            className="list-element"
            onClick={onClick ? () => onClick(item) : undefined}
            style={{cursor: onClick ? "pointer" : "default"}}
        >
            <div className="list-element-container">
                {render(item)}
            </div>
        </div>
    );
}

export default ListElement;